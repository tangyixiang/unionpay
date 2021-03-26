package com.sky.unionpay.service;

import com.sky.unionpay.annotation.PayChannel;
import com.sky.unionpay.model.PayOrder;
import com.sky.unionpay.model.merchant.Merchant;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PayService {

    @Autowired
    private ApplicationContext applicationContext;

    public void createOrder() {

    }


    public void pay(String payType, Merchant merchant, PayOrder payOrder) {
        Map<String, Object> payBeans = applicationContext.getBeansWithAnnotation(PayChannel.class);
        Object payBean = null;
        for (String key : payBeans.keySet()) {
            Object o = payBeans.get(key);
            PayChannel payChannelAnnotation = o.getClass().getAnnotation(PayChannel.class);
            if (payChannelAnnotation.PayMethod().equals(payType)) {
                payBean = o;
                break;
            }
        }

    }


}
