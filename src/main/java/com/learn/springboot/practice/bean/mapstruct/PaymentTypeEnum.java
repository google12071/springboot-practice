package com.learn.springboot.practice.bean.mapstruct;

public enum PaymentTypeEnum {
    CASH(1, "CASH"),
    CHEQUE(2, "CHEQUE"),
    CARD_VISA(3, "CARD_VISA"),
    CARD_MASTER(4, "CARD_MASTER"),
    CARD_CREDIT(5, "CARD_CREDIT");

    PaymentTypeEnum(Integer code, String desc) {
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
