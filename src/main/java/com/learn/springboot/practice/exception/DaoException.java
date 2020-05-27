package com.learn.springboot.practice.exception;

/**
 * @ClassName DaoException
 * @Description:
 * @Author lfq
 * @Date 2020/5/13
 **/
public class DaoException extends RuntimeException {
    private String code;
    private String message;

    public DaoException(String message) {
        super(message);
        this.message = message;
    }
}
