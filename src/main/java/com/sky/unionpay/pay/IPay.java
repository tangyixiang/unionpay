package com.sky.unionpay.pay;

import com.sky.unionpay.model.PayOrder;
import com.sky.unionpay.model.PayResult;
import com.sky.unionpay.model.merchant.Merchant;

public interface IPay {


    /**
     * 支付方式校验
     *
     * @param merchant
     * @param payOrder
     * @return
     */
    boolean canPay(Merchant merchant, PayOrder payOrder);

    /**
     * 支付
     *
     * @param merchant 商户信息
     * @param payOrder 支付订单
     * @return 支付结果
     */
    PayResult pay(Merchant merchant, PayOrder payOrder);

}
