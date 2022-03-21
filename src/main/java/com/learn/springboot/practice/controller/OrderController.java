package com.learn.springboot.practice.controller;

import com.learn.springboot.practice.metric.PrometheusCustomMonitor;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@RestController
public class OrderController {
    @Resource
    private PrometheusCustomMonitor monitor;

    @Autowired
    private MeterRegistry registry;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/order")
    public String order() {
        // 统计下单次数
        monitor.getOrderCount().increment();
        Random random = new Random();
        int amount = random.nextInt(100);
        // 统计金额
        monitor.getAmountSum().record(amount);
        return "下单成功, 金额: " + amount;
    }

    @GetMapping("/gauge")
    public String gauge() {
        String metricName = "order_submit_gauge";
        Map<String, String> map = buildMap();
        String[] tagArr = buildTagArr(map);
        Gauge.builder(metricName, () -> buildValue(metricName, map, true)).tags(tagArr)
                .description("订单提交").register(registry);
        return "gauge";
    }

    @GetMapping("/summary")
    public String summary() {
        String metricName = "order_submit_summary";
        Map<String, String> map = buildMap();
        String[] tagArr = buildTagArr(map);
        DistributionSummary summary = DistributionSummary.builder(metricName)
                .description("订单提交summary")
                .publishPercentiles(0.5).publishPercentileHistogram()
                .sla((long) (10 * 0.3), (long) (10 * 0.6), (long) (10 * 0.9))
                .minimumExpectedValue(1L)
                .maximumExpectedValue(10L).tags(tagArr).
                        register(registry);
        summary.record(new Random().nextInt(10) + 1);
        return "summary";
    }

    private String[] buildTagArr(Map<String, String> map) {
        List<String> tags = new ArrayList<>();
        map.forEach((tagName, tagValue) -> {
            tags.add(tagName);
            tags.add(tagValue);
        });
        String[] tagArr = new String[tags.size()];
        tags.toArray(tagArr);
        return tagArr;
    }

    private Map<String, String> buildMap() {
        Map<String, String> map = new HashMap<>();
        String[] arr = new String[]{"S1001", "S1002", "S1003", "S1005", "S1005"};
        String[] type = new String[]{"recommend", "common"};
        map.put("supplier_id", arr[new Random().nextInt(arr.length - 1)]);
        map.put("status", String.valueOf(new Random().nextBoolean()));
        map.put("order_type", type[new Random().nextInt(arr.length - 1)]);
        return map;
    }

    private String buildKey(String metricName, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("prometheus").append(".").append(metricName).append(".");
        map.values().forEach(x -> sb.append(x).append("."));
        return sb.toString();
    }

    private Number buildValue(String metricName, Map<String, String> map, boolean increment) {
        String key = buildKey(metricName, map);
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        if (operations.get(key) != null) {
            if (increment) {
                return operations.increment(key);
            } else {
                return operations.decrement(key);
            }
        } else {
            redisTemplate.opsForValue().set(key, "1");
        }
        return 1;
    }
}
