package com.learn.springboot.practice.dp.chain;

import lombok.extern.slf4j.Slf4j;

/**
 * 责任链模式：
 * 使多个对象都有机会处理请求，从而避免了请求的发送者和接受者之间的耦合关系。将这些对象连成一条链，并沿着这条链传递该请求，直到有对象处理它为止。
 *
 * @author lfq
 */
@Slf4j
public abstract class Handler {

    /**
     * 指向下一个处理者
     */
    private Handler nextHandler;

    /***
     * 处理者能够处理的级别
     *
     */
    private int level;

    public Handler(int level) {
        this.level = level;
    }

    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    /**
     * 处理请求传递，注意final，子类不可重写
     *
     * @param request
     */
    public final void handleMessage(Request request) {
        if (level == request.getRequestLevel()) {
            this.echo(request);
        } else {
            if (this.nextHandler != null) {
                this.nextHandler.handleMessage(request);
            } else {
                log.info("已经到最尽头了");
            }
        }
    }


    /**
     * 抽象方法，子类实现
     *
     * @param request
     */
    public abstract void echo(Request request);
}
