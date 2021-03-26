package com.sky.unionpay.constant;

public enum MerchantTypeEnum {

    SELF_EMPLOYED(1, "自营商户"),
    THIRD_EMPLOYED(2, "第三方商户");


    private Integer state;

    private String stateName;

    private MerchantTypeEnum(Integer state, String stateName) {
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
