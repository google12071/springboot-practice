package com.learn.springboot.practice.service;

import com.learn.springboot.practice.pojo.Order;

import java.util.Map;

/**
 * 订单抽象
 *
 * @author lfq
 */
public interface OrderService {
    /**
     * 创建订单
     *
     * @return
     */
    Order create();

    /**
     * 发起支付
     *
     * @param id
     * @return
     */
    Order pay(int id);

    /**
     * 订单发货
     *
     * @param id
     * @return
     */
    Order deliver(int id);

    /**
     * 订单收货
     *
     * @param id
     * @return
     */
    Order receive(int id);

    /**
     * 获取所有订单信息
     *
     * @return
     */
    Map<Integer, Order> getOrders();
}
