package com.learn.springboot.practice.model.constant;

/**
 * @ClassName PhaseEnum
 * @Description:
 * @Author lfq
 * @Date 2020/4/22
 **/
public enum PhaseEnum {
    BABY(0, "幼年"),
    CHILDHOOD(1, "童年"),
    TEENAGER(2, "青少年"),
    MIDDLE(3, "中年"),
    OLD(4, "老年");

    PhaseEnum(Integer code, String name) {
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
