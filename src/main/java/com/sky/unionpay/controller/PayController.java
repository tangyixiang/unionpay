package com.sky.unionpay.controller;

import com.sky.unionpay.dto.CreateOrderRequestDto;
import com.sky.unionpay.model.PayOrder;
import com.sky.unionpay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.util.StringUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/union")
public class PayController {

    @Autowired
    private PayService payService;


    @PostMapping("/v1/pay")
    public PayOrder createOrder(@RequestBody CreateOrderRequestDto createOrderRequestDto) {
        validateData(createOrderRequestDto);


        payService.pay(createOrderRequestDto);
        return null;
    }


    private void validateData(CreateOrderRequestDto createOrderRequestDto) {
        if (!StringUtils.hasText(createOrderRequestDto.getPayType())) {
            throw new RuntimeException("支付类型不能为空");
        }
        if (!StringUtils.hasText(createOrderRequestDto.getMerchantId())) {
            throw new RuntimeException("商户ID不能为空");
        }
        if (!StringUtils.hasText(createOrderRequestDto.getBusinessId())) {
            throw new RuntimeException("业务系统订单号不能为空");
        }
    }

}
