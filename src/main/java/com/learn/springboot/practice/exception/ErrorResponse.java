package com.learn.springboot.practice.exception;

import lombok.Data;

/**
 * @ClassName ErrorResponse
 * @Description:
 * @Author lfq
 * @Date 2020/4/29
 **/
@Data
public class ErrorResponse {
    private String message;
    private Integer errorCode;

    public ErrorResponse(Exception e) {
        this.message = e.getMessage();
    }

}
