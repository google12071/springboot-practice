package com.learn.springboot.practice.dp.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * 具体装饰类，增加装饰行为
 */
@Slf4j
public class ConcreteDecorator extends Decorator {
    /**
     * 装配构件
     *
     * @param component
     */
    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        //增加行为
        addBehavior();
    }

    public void addBehavior() {
        log.info("为具体构件角色增加额外的功能addBehavior()");
    }
}
