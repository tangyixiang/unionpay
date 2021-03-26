package com.sky.unionpay.controller;

import com.sky.unionpay.dto.CreateOrderRequestDto;
import com.sky.unionpay.model.PayOrder;
import com.sky.unionpay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
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

        payService.pay("JSAPI", null, null);
        return null;
    }


}
