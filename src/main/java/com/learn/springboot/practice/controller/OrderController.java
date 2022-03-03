package com.learn.springboot.practice.controller;

import com.learn.springboot.practice.metric.PrometheusCustomMonitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

@Slf4j
@RestController
public class OrderController {
    @Resource
    private PrometheusCustomMonitor monitor;

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
}
