package com.sky.unionpay.config;

import com.sky.unionpay.Exception.SignException;
import com.sky.unionpay.constant.ErrorCode;
import com.sky.unionpay.model.ResultMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SignException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResultMessage signError(SignException e){
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setCode(ErrorCode.SIGN_ERROR);
        resultMessage.setMessage(e.getMessage());
        return resultMessage;
    }


}
