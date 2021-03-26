package com.sky.unionpay.constant;


public enum PayStateEnum {

    PAYING(10, "支付中"),
    PAYED(20, "支付完成"),
    PAY_FAIL(-1, "支付失败");


    private Integer state;

    private String stateName;

    private PayStateEnum(Integer state, String stateName) {
        this.state = state;
        this.stateName = stateName;
    }

    public Integer getState() {
        return state;
    }

    public String getStateName() {
        return stateName;
    }
}
