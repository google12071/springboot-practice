package com.learn.springboot.practice.pojo;

import com.learn.springboot.practice.enums.OrderStatusEnum;
import lombok.Data;

/**
 * 订单
 *
 * @author lfq
 */
@Data
public class Order {
    private int id;
    private OrderStatusEnum status;
}
