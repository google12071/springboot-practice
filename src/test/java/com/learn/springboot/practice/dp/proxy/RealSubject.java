package com.learn.springboot.practice.dp.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 真实主题
 */
@Slf4j
public class RealSubject implements Subject {
    @Override
    public void request() {
        log.info("访问真实主题方法...");
    }
}
