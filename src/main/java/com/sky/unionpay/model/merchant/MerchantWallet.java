package com.sky.unionpay.model.merchant;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MerchantWallet {

    private String id;

    private String merchantId;

    private Long totalRevenue;

    private Long balance;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
