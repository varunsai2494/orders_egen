package com.egen.orders.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    ORDER_NOT_FOUND(60, "Order is not exit!"),
    ORDER_STATUS_ERROR(61, "Status is not correct");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
