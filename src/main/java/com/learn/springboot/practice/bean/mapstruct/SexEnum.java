package com.learn.springboot.practice.bean.mapstruct;

/**
 * @author lfq
 */

public enum SexEnum {
    MEN(1, "男"),
    WOMEN(2, "女");

    SexEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
