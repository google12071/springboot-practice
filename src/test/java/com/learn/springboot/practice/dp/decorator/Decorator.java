package com.learn.springboot.practice.dp.decorator;

/**
 * 抽象装饰者
 */
public class Decorator {
    private Component component;

    /**
     * 注入组件
     *
     * @param component
     */
    public Decorator(Component component) {
        this.component = component;
    }

    public void operation() {
        component.operation();
    }
}
