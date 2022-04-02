package com.learn.springboot.practice.dp.observer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteObserverB implements Observer {
    @Override
    public void process() {
        log.info("ConcreteObserverB接收到消息并处理");
    }
}
