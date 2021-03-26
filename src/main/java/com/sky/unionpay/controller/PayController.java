package com.sky.unionpay.controller;

import com.sky.unionpay.dto.CreateOrderRequestDto;
import com.sky.unionpay.model.PayOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/union")
public class PayController {


    @PostMapping("/v1/pay")
    public PayOrder createOrder(CreateOrderRequestDto createOrderRequestDto){



        return null;
    }



}
