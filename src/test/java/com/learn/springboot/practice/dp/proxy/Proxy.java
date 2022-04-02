package com.learn.springboot.practice.dp.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 代理类
 */
@Slf4j
public class Proxy implements Subject {
    private RealSubject realSubject;

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        //前置处理
        preRequest();
        //真实处理
        realSubject.request();
        //后置处理
        afterRequest();
    }

    private void preRequest() {
        log.info("访问真实主题之前的预处理。");
    }

    private void afterRequest() {
        log.info("访问真实主题之后的后续处理。");
    }
}
