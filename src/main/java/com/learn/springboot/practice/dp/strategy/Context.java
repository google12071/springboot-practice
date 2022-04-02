package com.learn.springboot.practice.dp.strategy;

/**
 * 封装策略，屏蔽高层模块对策略、算法的直接访问，屏蔽可能存在的策略变化
 *
 * @author lfq
 */
public class Context {
    private Strategy strategy = null;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void doStrategy() {
        strategy.strategyMethod();
    }

}
