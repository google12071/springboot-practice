package com.learn.springboot.practice.dp.observer;

public class ObserverPattern {
    public static void main(String[] args) {
        Subject subject = new ConcreteSubject(1);
        Observer obsA = new ConcreteObserverA();
        Observer obsB = new ConcreteObserverB();
        subject.add(obsA);
        subject.add(obsB);

        //通知观察者
        subject.notifyMethod();
    }
}
