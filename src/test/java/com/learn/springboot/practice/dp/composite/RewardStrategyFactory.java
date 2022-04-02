package com.learn.springboot.practice.dp.composite;

import lombok.extern.slf4j.Slf4j;

/**
 * 发奖策略工厂
 */
@Slf4j
public class RewardStrategyFactory extends StrategyFactory {
    @Override
    RewardStrategy createStrategy(Class c) {
        RewardStrategy product = null;
        try {
            product = (RewardStrategy) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {

        }
        return product;
    }
}
