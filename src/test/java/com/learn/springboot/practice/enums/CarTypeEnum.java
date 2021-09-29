package com.learn.springboot.practice.enums;

public enum CarTypeEnum {
    SUV("SUV", "SUV"),
    LIMOUSINE("limousine", "轿车"),
    BUS("bus", "公交车");

    CarTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
