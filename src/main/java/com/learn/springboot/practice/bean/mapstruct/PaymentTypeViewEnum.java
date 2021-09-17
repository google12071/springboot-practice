package com.learn.springboot.practice.bean.mapstruct;

public enum PaymentTypeViewEnum {
    CASH((byte) 1, "CASH"),
    CHEQUE((byte) 2, "CHEQUE"),
    CARD((byte) 3, "CARD");

    PaymentTypeViewEnum(Byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Byte code;
    private String desc;

    public Byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
