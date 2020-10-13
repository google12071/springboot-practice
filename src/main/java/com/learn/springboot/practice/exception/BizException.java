package com.learn.springboot.practice.exception;

/**
 * @ClassName BizException
 * @Description:
 * @Author lfq
 * @Date 2020/10/12
 **/
public class BizException extends RuntimeException {
    private String code;
    private String message;

    public BizException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
