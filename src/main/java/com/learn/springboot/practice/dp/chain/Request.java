package com.learn.springboot.practice.dp.chain;

import lombok.Data;

/**
 * 责任链模式处理请求
 *
 * @author lfq
 */
@Data
public class Request {
    private int requestLevel;
    private String message;

    public Request() {
    }

    public Request(int requestLevel, String message) {
        this.requestLevel = requestLevel;
        this.message = message;
    }
}
