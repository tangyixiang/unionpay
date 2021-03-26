package com.sky.unionpay.pay.wechat;

import com.sky.unionpay.annotation.PayChannel;
import com.sky.unionpay.constant.PayChannels;
import com.sky.unionpay.model.PayOrder;
import com.sky.unionpay.model.PayResult;
import com.sky.unionpay.pay.ICloseOrder;
import com.sky.unionpay.pay.IPayState;
import com.sky.unionpay.pay.IRefund;

@PayChannel(value = PayChannels.WECHAT)
public class WechatChannelHandler implements IRefund, IPayState, ICloseOrder {

    @Override
    public PayResult refund(PayOrder refundOrder) {
        return null;
    }

    @Override
    public boolean close(PayOrder payOrder) {
        return false;
    }

    @Override
    public PayResult queryState(PayOrder payOrder) {
        return null;
    }
}
