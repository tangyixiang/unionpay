package com.sky.unionpay.pay.wechat;

import com.sky.unionpay.annotation.PayChannel;
import com.sky.unionpay.constant.PayChannelName;
import com.sky.unionpay.model.PayOrder;
import com.sky.unionpay.model.PayResult;
import com.sky.unionpay.model.merchant.Merchant;
import com.sky.unionpay.pay.IPay;
import org.springframework.stereotype.Component;

@PayChannel(value = PayChannelName.WECHAT)
@Component
public class WeChatPay implements IPay {

    @Override
    public PayResult pay(Merchant merchant, PayOrder payOrder) {
        return null;
    }

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
