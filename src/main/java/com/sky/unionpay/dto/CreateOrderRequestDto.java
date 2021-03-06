package com.sky.unionpay.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateOrderRequestDto implements Serializable {

    private String businessId;

    private long amount;

    private String merchantId;

    private String payType;

    private String openId;

    private String resultNoticeUrl;

    private String authCode;

    private String sign;

    private String version;
}
