package com.sky.unionpay.model;

import lombok.Data;

@Data
public class PayConfig {

    private String id;

    private String requestSecret;

    private String wechatMerchantId;

    private String wechatAppId;

    private String wechatAppSecret;

    private String wechatKey;

    private String wechatCertPath;

    private String aliAppId;

    private String aliPrivateKey;

    private String aliPublicKey;

}
