package com.learn.springboot.practice.enums;

/**
 * 订单状态
 *
 * @author lfq
 */

public enum OrderStatusEnum {
    // 待支付
    WAIT_PAYMENT,
    //待发货
    WAIT_DELIVER,
    //待收货
    WAIT_RECEIVE,
    //已完成
    FINISH;
}
