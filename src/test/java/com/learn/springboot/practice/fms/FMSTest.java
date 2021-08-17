package com.learn.springboot.practice.fms;

import com.learn.springboot.practice.BaseTest;
import com.learn.springboot.practice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class FMSTest extends BaseTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void orderStates() {
        Thread.currentThread().setName("主线程");
        orderService.create();
        orderService.create();
        orderService.pay(1);
        orderService.deliver(2);
        orderService.receive(2);
        System.out.println("全部订单状态：" + orderService.getOrders());
    }
}

