package com.sky.unionpay.constant;


/**
 * 支付类型枚举
 */
public enum PayTypeEnum {

    WECHAT_JSAPI("JSAPI", "公众号支付"),
    WECHAT_MWEB("MWEB", "H5支付"),
    WECHAT_MINI("MINI", "小程序"),
    WECHAT_MICROPAY("MICROPAY", "微信刷卡支付（被扫支付）"),

    ALI_NATIVE("NATIVE", "支付宝扫码支付"),
    ALI_BARCODE("BARCODE", "支付宝被扫支付");

    private String code;
    private String desc;

    private PayTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
