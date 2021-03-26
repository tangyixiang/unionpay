package com.sky.unionpay.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付类型
 */
public class PayTypes {

    public static final String JSAPI = "JSAPI";
    public static final String MWEB = "MWEB";
    public static final String MINI = "MINI";
    public static final String MICROPAY = "MICROPAY";
    public static final String NATIVE = "NATIVE";
    public static final String BARCODE = "BARCODE";

    public static final Map<String, String> payTypeMap = new HashMap<>();

    static {
        payTypeMap.put(PayTypes.JSAPI, PayTypeEnum.WECHAT_JSAPI.getDesc());
        payTypeMap.put(PayTypes.MWEB, PayTypeEnum.WECHAT_MWEB.getDesc());
        payTypeMap.put(PayTypes.MINI, PayTypeEnum.WECHAT_MINI.getDesc());
        payTypeMap.put(PayTypes.MICROPAY, PayTypeEnum.WECHAT_MICROPAY.getDesc());

        payTypeMap.put(PayTypes.NATIVE, PayTypeEnum.ALI_NATIVE.getDesc());
        payTypeMap.put(PayTypes.BARCODE, PayTypeEnum.ALI_BARCODE.getDesc());
    }

    public static String getName(String payType) {
        return payTypeMap.get(payType);
    }

}
