package com.learn.springboot.practice.dp.observer;

/**
 *
 *** 观察者模式包含角色：
 ************* Subject: 被观察目标 ***********************************************************************
 ************* ConcreteSubject: 具体被观察目标   ***********************************************************************
 ************* Observer: 观察者 *******************************************************************
 ************* ConcreteObserver: 具体观察者 *******************************************************************
 * *******************************************************************************************************
 *   优点：将复杂的串行处理逻辑变为单元化的独立处理逻辑，被观察者只是按照自己的逻辑发出消息，不用关心谁来消费消息，每个观察者只处理自己关心的内容。逻辑相互隔离带来简单清爽的代码结构。
 *   缺点：观察者较多时，可能会花费一定的开销来发消息，但这个消息可能仅一个观察者消费。
 ********************************************************************************************************/