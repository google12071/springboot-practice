package com.learn.springboot.practice.dp.chain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 责任链模式(传递的对象)
 */
@Data
public class ChainRequest {
    private String name;
    private BigDecimal amount;

    public ChainRequest(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }
}
