package com.sky.unionpay.model.merchant;

import com.sky.unionpay.model.PayChannel;
import com.sky.unionpay.model.PayConfig;
import lombok.Data;

import java.util.List;

@Data
public class Merchant extends MerchantBaseInfo {

    private Merchant parent;

    private PayConfig payConfig;

    private List<PayChannel> payChannels;

}
