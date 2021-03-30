package com.sky.unionpay.constant;

public enum StateEnum {

    ENABLE(1, "启用"),
    DISABLE(-1, "禁用");

    private Integer code;
    private String stateName;

    private StateEnum(Integer code, String stateName) {
        this.code = code;
        this.stateName = stateName;
    }

    public Integer getCode() {
        return code;
    }

    public String getStateName() {
        return stateName;
    }
}
