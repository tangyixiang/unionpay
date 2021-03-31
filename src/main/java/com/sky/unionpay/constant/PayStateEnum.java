package com.sky.unionpay.constant;


public enum PayStateEnum {

    PAYING(10, "支付中"),
    PAYED(20, "支付成功"),
    PAY_FAIL(-1, "支付失败");


    private Integer code;
    private String stateName;

    private PayStateEnum(Integer state, String stateName) {
        this.code = state;
        this.stateName = stateName;
    }

    public Integer getCode() {
        return code;
    }

    public String getStateName() {
        return stateName;
    }
}
