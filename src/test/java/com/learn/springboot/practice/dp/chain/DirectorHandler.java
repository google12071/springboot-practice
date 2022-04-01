package com.learn.springboot.practice.dp.chain;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class DirectorHandler implements ChainHandler {
    @Override
    public Boolean execute(ChainRequest request) {
        // 如果超过1000元，处理不了，交下一个处理:
        if (request.getAmount().compareTo(BigDecimal.valueOf(10000)) > 0) {
            return null;
        }
        return true;
    }
}
