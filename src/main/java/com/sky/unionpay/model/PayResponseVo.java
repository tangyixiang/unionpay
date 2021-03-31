package com.sky.unionpay.model;

import lombok.Data;

@Data
public class PayResponseVo {

    private int state;

    private boolean success;    //支付结果

    private Object data;
}
