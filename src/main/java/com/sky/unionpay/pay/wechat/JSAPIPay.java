package com.sky.unionpay.pay.wechat;

import com.sky.unionpay.annotation.PayChannel;
import com.sky.unionpay.constant.PayChannels;
import com.sky.unionpay.constant.PayTypes;
import com.sky.unionpay.model.PayOrder;
import com.sky.unionpay.model.PayResult;
import com.sky.unionpay.model.merchant.Merchant;
import com.sky.unionpay.pay.IPay;
import org.springframework.stereotype.Component;

@Component
@PayChannel(value = PayChannels.WECHAT, PayMethod = PayTypes.JSAPI)
public class JSAPIPay implements IPay {

    @Override
    public boolean canPay(Merchant merchant, PayOrder payOrder) {
        return false;
    }

    @Override
    public PayResult pay(Merchant merchant, PayOrder payOrder) {
        return null;
    }
}
