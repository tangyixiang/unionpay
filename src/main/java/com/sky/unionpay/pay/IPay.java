package com.sky.unionpay.pay;

import com.sky.unionpay.model.PayOrder;
import com.sky.unionpay.model.PayResult;
import com.sky.unionpay.model.merchant.Merchant;

public interface IPay {

    /**
     * 支付
     *
     * @param merchant  商户信息
     * @param payOrder  支付订单
     * @return          支付结果
     */
    PayResult pay(Merchant merchant, PayOrder payOrder);

    /**
     * 退款
     *
     * @param refundOrder   退款订单
     * @return              退款结果
     */
    PayResult refund(PayOrder refundOrder);

    /**
     * 关闭
     *
     * @param payOrder  支付订单
     * @return          支付结果
     */
    boolean close(PayOrder payOrder);

    /**
     * 查询支付状态
     *
     * @param payOrder  支付订单
     * @return          支付结果
     */
    PayResult queryState(PayOrder payOrder);

}
