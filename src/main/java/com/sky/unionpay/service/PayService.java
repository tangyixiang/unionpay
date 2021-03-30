package com.sky.unionpay.service;

import com.sky.unionpay.annotation.PaySource;
import com.sky.unionpay.constant.OrderTypes;
import com.sky.unionpay.constant.PayStateEnum;
import com.sky.unionpay.dto.CreateOrderRequestDto;
import com.sky.unionpay.model.PayOrder;
import com.sky.unionpay.model.PayResult;
import com.sky.unionpay.model.merchant.Merchant;

import com.sky.unionpay.pay.IPay;
import com.sky.unionpay.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class PayService extends BaseService {

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private PayOrderService payOrderService;


    public void pay(CreateOrderRequestDto createOrderRequestDto) {
        IPay payBean = getPayBean(createOrderRequestDto.getPayType());
        Merchant merchant = merchantService.findById(createOrderRequestDto.getMerchantId());
        //TODO 接口幂等 查询订单是否已经创建支付订单了,状态不是关闭的
        Integer payChannel = getPayChannel(payBean);

        PayOrder payOrder = buildPayOrder(createOrderRequestDto, payChannel);
        payBean.canPay(merchant, payOrder);

        PayResult payResult = payBean.pay(merchant, payOrder);
        int state = payResult.getState();
        if (state == PayStateEnum.PAYED.getState()) {
            payOrder.setState(PayStateEnum.PAYED.getState());
            notifyBusinessSys(payOrder);
        } else if (state == PayStateEnum.PAYING.getState()) {
            payOrder.setState(PayStateEnum.PAYING.getState());
        } else if (state == PayStateEnum.PAY_FAIL.getState()) {
            payOrder.setState(PayStateEnum.PAY_FAIL.getState());
            notifyBusinessSys(payOrder);
        }
        payOrderService.saveOrUpdate(payOrder);

    }

    // 通知业务系统
    @Async
    private void notifyBusinessSys(PayOrder payOrder) {
        String noticeUrl = payOrder.getNoticeUrl();

    }

    private Integer getPayChannel(IPay payBean) {
        PaySource paySource = payBean.getClass().getAnnotation(PaySource.class);
        return paySource.value();
    }

    private PayOrder buildPayOrder(CreateOrderRequestDto createOrderRequestDto, Integer payChannel) {
        PayOrder payOrder = new PayOrder();

        payOrder.setId(IDUtils.identifyId());
        payOrder.setMerchantId(createOrderRequestDto.getMerchantId());
        payOrder.setAmount(createOrderRequestDto.getAmount());
        payOrder.setBusinessId(createOrderRequestDto.getBusinessId());
        payOrder.setPayType(createOrderRequestDto.getPayType());
        payOrder.setPayChannel(payChannel);
        payOrder.setState(PayStateEnum.PAYING.getState());
        payOrder.setType(OrderTypes.payOrder);
        payOrder.setNoticeUrl(createOrderRequestDto.getResultNoticeUrl());
        payOrder.setCreateTime(LocalDateTime.now());

        payOrderService.saveOrUpdate(payOrder);
        return payOrder;
    }


}
