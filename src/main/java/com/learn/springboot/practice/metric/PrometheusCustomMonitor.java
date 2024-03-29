package com.learn.springboot.practice.metric;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Prometheus自定义指标计数器演示
 *
 * @author lfq
 */
@Slf4j
@Component
public class PrometheusCustomMonitor {
    @Autowired
    private MeterRegistry registry;

    /**
     * 订单发起次数
     */
    private Counter orderCount;

    /**
     * 金额统计
     */
    private DistributionSummary amountSum;


    @PostConstruct
    private void init() {
        orderCount = registry.counter("order_request_count", "order", "test-svc");
        amountSum = registry.summary("order_amount_sum", "orderAmount", "test-svc");
    }

    public Counter getOrderCount() {
        return orderCount;
    }

    public DistributionSummary getAmountSum() {
        return amountSum;
    }
}
