package com.sky.unionpay.model;

import lombok.Data;

@Data
public class RefundOrderDto {

    public PayOrder payOrder; //支付订单

    public long refundAmount;

}
