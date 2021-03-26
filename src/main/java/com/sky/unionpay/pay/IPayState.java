package com.sky.unionpay.pay;

import com.sky.unionpay.model.PayOrder;
import com.sky.unionpay.model.PayResult;

public interface IPayState {

    /**
     * 查询支付状态
     *
     * @param payOrder  支付订单
     * @return          支付结果
     */
    PayResult queryState(PayOrder payOrder);

}
