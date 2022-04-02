package com.learn.springboot.practice.dp.observer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteObserverA implements Observer {
    @Override
    public void process() {
        log.info("ConcreteObserverA接收到消息并处理");
    }
}
