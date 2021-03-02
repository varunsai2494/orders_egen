package com.egen.orders.enums;

public enum PaymentStatusEnum implements CodeEnum {
    ACCEPTED(1,"payment_accepted"),
    FAILED(2,"payment_failed");

    private  int code;
    private String msg;

    PaymentStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
