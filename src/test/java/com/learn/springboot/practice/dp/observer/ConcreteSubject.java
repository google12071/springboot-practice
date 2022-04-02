package com.learn.springboot.practice.dp.observer;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 具体目标
 */
@Slf4j
@Data
public class ConcreteSubject extends Subject {
    private Integer state;

    public ConcreteSubject(Integer state) {
        this.state = state;
    }


    @Override
    public void notifyMethod() {
        log.info("具体目标状态发生改变...");
        for (Observer obs : observerList) {
            obs.process();
        }
    }
}
