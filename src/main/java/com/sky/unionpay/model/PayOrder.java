package com.sky.unionpay.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PayOrder {

    private String id;

    private String merchantId;

    private String businessId;

    private Long amount;

    private Long refundAmount;

    private Integer type; //订单类型

    private Integer state;

    private Integer payChannel; //支付渠道

    private String payType;     //支付方式 JSAPI

    private Integer noticeState;

    private Integer noticeTime; //通知次数

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime lastNoticeTime;   //上次通知时间

}
