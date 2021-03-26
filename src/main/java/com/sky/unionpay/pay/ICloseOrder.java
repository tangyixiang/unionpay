package com.sky.unionpay.pay;

import com.sky.unionpay.model.PayOrder;

public interface ICloseOrder {

    /**
     * 关闭
     *
     * @param payOrder  支付订单
     * @return          支付结果
     */
    boolean close(PayOrder payOrder);

}
