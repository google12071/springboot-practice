package com.learn.springboot.practice.dp.factory;

/**
 * 抽象的工厂
 *
 * @author lfq
 */
public abstract class Factory<T> {
    /**
     * 创建产品
     *
     * @param tClass
     * @return
     */
    public abstract Product createProduct(Class<T> tClass);
}
