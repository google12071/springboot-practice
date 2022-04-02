package com.learn.springboot.practice.dp.chain;

/**
 * @author lfq
 */
public class ChainClient {
    public static void main(String[] args) {
        Handler handlerA = new ConcreteHandlerA(1);
        Handler handlerB = new ConcreteHandlerB(2);
        handlerA.setNextHandler(handlerB);
        Request request = new Request(3, "request");
        handlerA.handleMessage(request);
    }

}
