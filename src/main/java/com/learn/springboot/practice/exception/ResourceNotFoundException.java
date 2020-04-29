package com.learn.springboot.practice.exception;

import lombok.Data;

/**
 * @ClassName ResourceNotFoundException
 * @Description:自定义异常
 * @Author lfq
 * @Date 2020/4/29
 **/
@Data
public class ResourceNotFoundException extends RuntimeException {
    private String message;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
