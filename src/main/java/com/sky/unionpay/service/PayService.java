package com.sky.unionpay.service;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sky.unionpay.Exception.NotifyBusinessException;
import com.sky.unionpay.annotation.PaySource;
import com.sky.unionpay.constant.OrderTypes;
import com.sky.unionpay.constant.PayStateEnum;
import com.sky.unionpay.dto.CreateOrderRequestDto;
import com.sky.unionpay.dto.NotifyPayResultRequestDto;
import com.sky.unionpay.dto.NotifyPayResultResponseDto;
import com.sky.unionpay.model.PayOrder;
import com.sky.unionpay.model.PayRequestParam;
import com.sky.unionpay.model.PayResult;
import com.sky.unionpay.model.merchant.Merchant;

import com.sky.unionpay.pay.IPay;
import com.sky.unionpay.util.IDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class PayService extends BaseService {

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private PayOrderService payOrderService;


    public PayResult pay(CreateOrderRequestDto createOrderRequestDto) {
        IPay payBean = getPayBean(createOrderRequestDto.getPayType());
        Integer payChannel = payBean.getPayChannel(payBean);
        Merchant merchant = merchantService.findEnableMerchant(createOrderRequestDto.getMerchantId());

        //TODO 接口幂等 查询订单是否已经创建支付订单了,状态不是关闭的


        PayRequestParam payRequestParam = new PayRequestParam(createOrderRequestDto.getOpenId(), createOrderRequestDto.getAuthCode());
        PayOrder payOrder = buildPayOrder(createOrderRequestDto, payChannel);
        payBean.canPay(merchant, payOrder);

        PayResult payResult = payBean.pay(payRequestParam, merchant, payOrder);
        int state = payResult.getState();
        if (state == PayStateEnum.PAYED.getCode()) {
            payOrder.setState(PayStateEnum.PAYED.getCode());
            notifyBusinessSys(payOrder);
        } else if (state == PayStateEnum.PAYING.getCode()) {
            payOrder.setState(PayStateEnum.PAYING.getCode());
        } else if (state == PayStateEnum.PAY_FAIL.getCode()) {
            payOrder.setState(PayStateEnum.PAY_FAIL.getCode());
            notifyBusinessSys(payOrder);
        }
        payOrderService.saveOrUpdate(payOrder);
        return payResult;
    }

    // 通知业务系统
    @Async
    @Retryable(value = NotifyBusinessException.class, backoff = @Backoff(delay = 2000L, multiplier = 1.5))
    private void notifyBusinessSys(PayOrder payOrder) {
        String noticeUrl = payOrder.getNoticeUrl();
        NotifyPayResultRequestDto notifyPayResultRequestDto = new NotifyPayResultRequestDto();
        notifyPayResultRequestDto.setBusinessId(payOrder.getBusinessId());
        notifyPayResultRequestDto.setPayId(payOrder.getId());
        notifyPayResultRequestDto.setPayType(payOrder.getPayType());
        notifyPayResultRequestDto.setState(payOrder.getState());
        //TODO 对数据进行签名
        notifyPayResultRequestDto.setSign("123");
        boolean notifySuccess = false;
        String notifyResult = HttpUtil.post(noticeUrl, JSONUtil.toJsonStr(notifyPayResultRequestDto));
        NotifyPayResultResponseDto notifyPayResultResponseDto = JSONUtil.toBean(notifyResult, NotifyPayResultResponseDto.class);
        if (notifyPayResultResponseDto.getSuccess() == 1) {
            notifySuccess = true;
        }
        int noticeState = notifySuccess ? 1 : 2;
        payOrder.setNoticeState(noticeState);
        payOrder.setNoticeTime(payOrder.getNoticeTime() + 1);
        payOrder.setUpdateTime(LocalDateTime.now());
        payOrder.setLastNoticeTime(LocalDateTime.now());
        payOrderService.saveOrUpdate(payOrder);

        if (!notifySuccess) {
            log.error("业务系统通知失败,支付订单ID:{},业务订单ID:{}", payOrder.getId(), payOrder.getBusinessId());
            throw new NotifyBusinessException("业务系统通知失败");
        }

    }

    private PayOrder buildPayOrder(CreateOrderRequestDto createOrderRequestDto, Integer payChannel) {
        PayOrder payOrder = new PayOrder();

        payOrder.setId(IDUtils.identifyId());
        payOrder.setMerchantId(createOrderRequestDto.getMerchantId());
        payOrder.setAmount(createOrderRequestDto.getAmount());
        payOrder.setBusinessId(createOrderRequestDto.getBusinessId());
        payOrder.setPayType(createOrderRequestDto.getPayType());
        payOrder.setPayChannel(payChannel);
        payOrder.setState(PayStateEnum.PAYING.getCode());
        payOrder.setType(OrderTypes.payOrder);
        payOrder.setNoticeUrl(createOrderRequestDto.getResultNoticeUrl());
        payOrder.setCreateTime(LocalDateTime.now());

        payOrderService.saveOrUpdate(payOrder);
        return payOrder;
    }


}
