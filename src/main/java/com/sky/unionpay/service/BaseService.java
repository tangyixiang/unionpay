package com.sky.unionpay.service;

import com.sky.unionpay.Exception.NoSuchPayTypeException;
import com.sky.unionpay.annotation.PaySource;
import com.sky.unionpay.pay.ICloseOrder;
import com.sky.unionpay.pay.IPay;
import com.sky.unionpay.pay.IPayState;
import com.sky.unionpay.pay.IRefund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BaseService {

    @Autowired
    private ApplicationContext applicationContext;

    public IPay getPayBean(String payType) {
        Map<String, Object> payBeans = applicationContext.getBeansWithAnnotation(PaySource.class);
        IPay payBean = null;
        for (String key : payBeans.keySet()) {
            Object o = payBeans.get(key);
            PaySource paySourceAnnotation = o.getClass().getAnnotation(PaySource.class);
            if (paySourceAnnotation.PayMethod().equals(payType)) {
                payBean = (IPay) o;
                break;
            }
        }

        if (payBean == null) {
            throw new NoSuchPayTypeException("未知的支付类型");
        }
        return payBean;
    }

    public IPayState getQueryBean(Integer paySource) {
        IPayState queryBean = null;
        Map<String, IPayState> queryBeans = applicationContext.getBeansOfType(IPayState.class);
        for (String key : queryBeans.keySet()) {
            IPayState iPayState = queryBeans.get(key);
            PaySource paySourceAnnotation = iPayState.getClass().getAnnotation(PaySource.class);
            if (paySourceAnnotation.value() == paySource) {
                queryBean = iPayState;
            }
        }
        if (queryBean == null) {
            throw new NoSuchPayTypeException("未知的支付渠道");
        }
        return queryBean;
    }


    public IRefund getRefundBean(Integer paySource) {
        IRefund refundBean = null;
        Map<String, IRefund> queryBeans = applicationContext.getBeansOfType(IRefund.class);
        for (String key : queryBeans.keySet()) {
            IRefund iRefund = queryBeans.get(key);
            PaySource paySourceAnnotation = iRefund.getClass().getAnnotation(PaySource.class);
            if (paySourceAnnotation.value() == paySource) {
                refundBean = iRefund;
            }
        }
        if (refundBean == null) {
            throw new NoSuchPayTypeException("未知的支付渠道");
        }
        return refundBean;
    }

    public ICloseOrder getCloseBean(Integer paySource) {
        ICloseOrder closeBean = null;
        Map<String, ICloseOrder> queryBeans = applicationContext.getBeansOfType(ICloseOrder.class);
        for (String key : queryBeans.keySet()) {
            ICloseOrder iCloseBean = queryBeans.get(key);
            PaySource paySourceAnnotation = iCloseBean.getClass().getAnnotation(PaySource.class);
            if (paySourceAnnotation.value() == paySource) {
                closeBean = iCloseBean;
            }
        }
        if (closeBean == null) {
            throw new NoSuchPayTypeException("未知的支付渠道");
        }
        return closeBean;
    }


}
