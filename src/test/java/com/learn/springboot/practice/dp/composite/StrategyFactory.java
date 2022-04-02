package com.learn.springboot.practice.dp.composite;

/**
 * 抽象工厂
 *
 * @param <T>
 */
public abstract class StrategyFactory<T> {
    abstract RewardStrategy createStrategy(Class<T> c);
}
