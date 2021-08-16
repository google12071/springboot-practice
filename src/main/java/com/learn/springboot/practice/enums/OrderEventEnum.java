package com.learn.springboot.practice.enums;

/**
 * 订单状态变更事件
 *
 * @author lfq
 */
public enum OrderEventEnum {
    //支付
    PAYED,
    //发货
    DELIVERY,
    //签收
    RECEIVED;
}
