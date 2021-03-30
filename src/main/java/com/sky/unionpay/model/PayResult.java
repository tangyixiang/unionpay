package com.sky.unionpay.model;

import lombok.Data;

@Data
public class PayResult {

    private int state;

    private boolean success;    //支付结果

    private boolean Sync;       //同步支付 异步回调

    private PayOrder payOrder;  //支付订单
}
