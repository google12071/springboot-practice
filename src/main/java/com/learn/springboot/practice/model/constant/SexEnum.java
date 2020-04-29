package com.learn.springboot.practice.model.constant;

/**
 * @author lfq
 */

public enum SexEnum {
    MEN(1, "男"),
    WOMEN(0, "女");

    SexEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
