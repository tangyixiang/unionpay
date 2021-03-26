package com.sky.unionpay.model.merchant;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MerchantBaseInfo {

    protected String id;

    protected String name;
    // 商户类型
    protected Integer type;

    protected Integer state;

    protected LocalDateTime createTime;

    protected LocalDateTime updateTime;

}
