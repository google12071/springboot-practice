package com.learn.springboot.practice.dp.chain;

import lombok.extern.slf4j.Slf4j;

/**
 * 具体处理器B
 *
 * @author lfq
 */
@Slf4j
public class ConcreteHandlerB extends Handler {
    public ConcreteHandlerB(int level) {
        super(level);
    }

    @Override
    public void echo(Request request) {
        log.info("deal with by B,level:{}", request.getRequestLevel());
    }
}
