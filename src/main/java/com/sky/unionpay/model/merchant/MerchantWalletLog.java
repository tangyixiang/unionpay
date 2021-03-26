package com.sky.unionpay.model.merchant;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MerchantWalletLog {

    private String id;

    private String walletId;

    private String payOrderId;

    private Long amount;

    // 1 收入 2 提现
    private Integer type;

    private Integer payChannel;

    private LocalDateTime createTime;

}
