package com.sky.unionpay.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class NotifyPayResultRequestDto implements Serializable {

    private String businessId;

    private String payId;

    private Integer state;

    private String payType;

    private String sign;

    private String version;
}
