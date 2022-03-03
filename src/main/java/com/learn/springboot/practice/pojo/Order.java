package com.learn.springboot.practice.pojo;

import com.learn.springboot.practice.enums.OrderStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 订单
 *
 * @author lfq
 */
@Data
public class Order {
    private int id;
    private String orderId;
    private Integer amount;
    private String channel;
    private LocalDateTime createTime;
    private OrderStatusEnum status;
}
