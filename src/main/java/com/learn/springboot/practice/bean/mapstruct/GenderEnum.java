package com.learn.springboot.practice.bean.mapstruct;

/**
 * 性别枚举
 * @author lfq
 */
public enum GenderEnum {
    MALE(true, "雌性"),
    FEMALE(false, "雄性");

    private boolean code;
    private String desc;

    GenderEnum(boolean code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public boolean isCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
