package com.sky.unionpay.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PayOrder {

    private String id;

    private String merchantId;

    private String businessId;

    private long amount;

    private long refundAmount;

    private Integer type; //订单类型 1 支付订单  2 退款订单

    private Integer state;

    private Integer payChannel; //支付渠道

    private String payType;     //支付方式 JSAPI

    private String noticeUrl;

    private int noticeState;    // 0 未通知  1 通知成功  2 通知失败

    private int noticeTime; //通知次数

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime lastNoticeTime;   //上次通知时间

}
