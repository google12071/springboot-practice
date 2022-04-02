package com.learn.springboot.practice.dp.chain;

import lombok.extern.slf4j.Slf4j;

/**
 * 具体处理器A
 * @author lfq
 */
@Slf4j
public class ConcreteHandlerA extends Handler{
    public ConcreteHandlerA(int level) {
        super(level);
    }

    @Override
    public void echo(Request request) {
        log.info("deal with by A,level:{}", request.getRequestLevel());
    }
}
