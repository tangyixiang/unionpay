package com.sky.unionpay.pay;

import com.sky.unionpay.Exception.NoSuchPayChannelException;
import com.sky.unionpay.annotation.PaySource;
import com.sky.unionpay.constant.StateEnum;
import com.sky.unionpay.model.PayChannel;
import com.sky.unionpay.model.PayOrder;
import com.sky.unionpay.model.PayRequestParam;
import com.sky.unionpay.model.PayResult;
import com.sky.unionpay.model.merchant.Merchant;

import java.util.List;
import java.util.stream.Collectors;

public interface IPay {

    /**
     * 支付
     *
     * @param merchant 商户信息
     * @param payOrder 支付订单
     * @return 支付结果
     */
    PayResult pay(PayRequestParam payRequestParam, Merchant merchant, PayOrder payOrder);


    /**
     * 支付方式校验
     */
    default IPay canPay(Merchant merchant, PayOrder payOrder) throws NoSuchPayChannelException {
        List<PayChannel> payChannels = merchant.getPayChannels().stream()
                .filter(payChannel -> payChannel.getState() == StateEnum.ENABLE.getCode())
                .filter(payChannel -> payChannel.getType().equals(payOrder.getPayType()))
                .collect(Collectors.toList());
        if (payChannels.size() == 0) {
            throw new NoSuchPayChannelException("商户没有" + payOrder.getPayType() + "支付渠道权限");
        }
        return this;
    }

    /**
     * 获取支付渠道
     *
     * @param payBean   支付实现类
     */
    default Integer getPayChannel(IPay payBean) {
        PaySource paySource = payBean.getClass().getAnnotation(PaySource.class);
        return paySource.value();
    }


}
