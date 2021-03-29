package com.sky.unionpay.Exception;

public class NoSuchPayTypeException extends RuntimeException {

    public NoSuchPayTypeException() {
    }

    public NoSuchPayTypeException(String message) {
        super(message);
    }
}
