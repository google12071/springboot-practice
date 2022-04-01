package com.learn.springboot.practice.dp.chain;

/**
 * 责任链处理器
 */
public interface ChainHandler {
    /**
     * 责任链上节点，每个节点处理责任链对象，并决定是否递交下一个处理器
     *
     * @param request
     * @return
     */
    Boolean execute(ChainRequest request);
}
