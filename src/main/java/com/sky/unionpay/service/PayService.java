package com.sky.unionpay.service;

import com.sky.unionpay.dto.CreateOrderRequestDto;
import com.sky.unionpay.model.PayOrder;
import com.sky.unionpay.model.merchant.Merchant;

import com.sky.unionpay.pay.IPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PayService extends BaseService {

    @Autowired
    private MerchantService merchantService;

    public void pay(CreateOrderRequestDto createOrderRequestDto) {
        IPay payBean = getPayBean(createOrderRequestDto.getPayType());
        merchantService.findById(createOrderRequestDto.getMerchantId());
        //TODO 接口幂等 查询订单是否已经创建支付订单了

    }


}
