package com.learn.springboot.practice.service;

import com.learn.springboot.practice.enums.OrderEventEnum;
import com.learn.springboot.practice.enums.OrderStatusEnum;
import com.learn.springboot.practice.pojo.Order;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @author lfq
 */
@Configuration
@WithStateMachine
public class OrderStateListener {

    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
    public boolean payTransition(Message<OrderEventEnum> message) {
        Order order = (Order) message.getHeaders().get("order");
        order.setStatus(OrderStatusEnum.WAIT_DELIVER);
        System.out.println("支付，状态机反馈信息：" + message.getHeaders());
        return true;
    }

    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
    public boolean deliverTransition(Message<OrderEventEnum> message) {
        Order order = (Order) message.getHeaders().get("order");
        order.setStatus(OrderStatusEnum.WAIT_RECEIVE);
        System.out.println("发货，状态机反馈信息：" + message.getHeaders());
        return true;
    }

    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
    public boolean receiveTransition(Message<OrderEventEnum> message) {
        Order order = (Order) message.getHeaders().get("order");
        order.setStatus(OrderStatusEnum.FINISH);
        System.out.println("收货，状态机反馈信息：" + message.getHeaders());
        return true;
    }
}
