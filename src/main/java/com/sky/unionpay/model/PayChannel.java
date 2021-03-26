package com.sky.unionpay.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PayChannel {

    private String id;

    private String payChannelName;

    private String type;

    private String merchantId;
    // 启用 禁用
    private Integer state;

    private LocalDateTime createTime;

}
