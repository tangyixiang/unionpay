package com.sky.unionpay.constant;

public enum CommonStateEnum {

    ENABLE(1, "启用"),
    DISABLE(-1, "禁用");


    private Integer state;

    private String stateName;

    private CommonStateEnum(Integer state, String stateName) {
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
