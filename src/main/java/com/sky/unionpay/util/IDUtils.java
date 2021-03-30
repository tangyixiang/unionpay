package com.sky.unionpay.util;

import java.util.UUID;

public class IDUtils {

    public static String identifyId(){
        return UUID.randomUUID().toString();
    }
}
