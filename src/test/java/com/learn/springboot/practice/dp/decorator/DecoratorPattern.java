package com.learn.springboot.practice.dp.decorator;

/**
 * 装饰者模式测试类（装饰前后，对调用者无感知）
 */
public class DecoratorPattern {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        component.operation();

        System.out.println("---------------------------------");
        Decorator decorator = new ConcreteDecorator(component);
        decorator.operation();
    }
}
