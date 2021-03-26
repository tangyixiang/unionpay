package com.sky.unionpay.aspect;

import com.sky.unionpay.dto.CreateOrderRequestDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestSignCheckAspect {

    @Pointcut("execution(* com.sky.unionpay.controller.PayController.createOrder*(..))")
    public void point() {

    }

    @Before("point()")
    public void signCheck(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        CreateOrderRequestDto createOrderRequestDto = (CreateOrderRequestDto) args[0];
        //TODO 校验签名,校验不过抛出异常
    }

}
