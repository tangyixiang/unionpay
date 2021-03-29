package com.sky.unionpay.Exception;

public class NoSuchPayChannelException extends RuntimeException {

    public NoSuchPayChannelException() {
    }

    public NoSuchPayChannelException(String message) {
        super(message);
    }
}
