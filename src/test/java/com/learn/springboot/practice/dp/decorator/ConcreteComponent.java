package com.learn.springboot.practice.dp.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * 具体构件
 */
@Slf4j
public class ConcreteComponent implements Component {
    public ConcreteComponent() {
        log.info("创建具体构件");
    }

    @Override
    public void operation() {
        log.info("调用具体构件的方法");
    }
}
