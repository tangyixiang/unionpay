package com.sky.unionpay.model.merchant;

import com.sky.unionpay.model.PayConfig;
import lombok.Data;

@Data
public class Merchant extends MerchantBaseInfo {

    private Merchant parent;

    private PayConfig payConfig;

}
