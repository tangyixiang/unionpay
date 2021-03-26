package com.sky.unionpay.pay;

import com.sky.unionpay.model.PayOrder;
import com.sky.unionpay.model.PayResult;

public interface IRefund {

    /**
     * 退款
     *
     * @param refundOrder   退款订单
     * @return              退款结果
     */
    PayResult refund(PayOrder refundOrder);

}
