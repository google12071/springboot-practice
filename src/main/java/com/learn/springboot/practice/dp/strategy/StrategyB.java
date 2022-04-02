package com.learn.springboot.practice.dp.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lfq
 */
@Slf4j
public class StrategyB implements Strategy {
    @Override
    public void strategyMethod() {
        log.info("strategyMethodB....");
    }
}
