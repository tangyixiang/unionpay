package com.sky.unionpay.pay.wechat;

import com.sky.unionpay.Exception.NoSuchPayChannelException;
import com.sky.unionpay.annotation.PaySource;
import com.sky.unionpay.constant.PayChannels;
import com.sky.unionpay.constant.PayTypes;
import com.sky.unionpay.constant.StateEnum;
import com.sky.unionpay.model.PayChannel;
import com.sky.unionpay.model.PayOrder;
import com.sky.unionpay.model.PayResult;
import com.sky.unionpay.model.merchant.Merchant;
import com.sky.unionpay.pay.IPay;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@PaySource(value = PayChannels.WECHAT, PayMethod = PayTypes.JSAPI)
public class JSAPIPay implements IPay {

    @Override
    public IPay canPay(Merchant merchant, PayOrder payOrder) throws NoSuchPayChannelException {

        List<PayChannel> payChannels = merchant.getPayChannels().stream()
                .filter(payChannel -> payChannel.getState() == StateEnum.ENABLE.getCode())
                .filter(payChannel -> payChannel.getType().equals(payOrder.getPayType()))
                .collect(Collectors.toList());
        if (payChannels.size() == 0) {
            throw new NoSuchPayChannelException("商户没有" + payOrder.getPayType() + "支付渠道权限");
        }
        return this;
    }

    @Override
    public PayResult pay(Merchant merchant, PayOrder payOrder) {
        return null;
    }
}
