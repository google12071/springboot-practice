package com.learn.springboot.practice.dp.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    protected List<Observer> observerList = new ArrayList<>();

    /**
     * 增加观察者方法
     *
     * @param observer
     */
    public void add(Observer observer) {
        observerList.add(observer);
    }


    /**
     * 删除观察者方法
     *
     * @param observer
     */
    public void remove(Observer observer) {
        observerList.remove(observer);
    }

    /**
     * 通知观察者方法
     */
    public abstract void notifyMethod();
}
