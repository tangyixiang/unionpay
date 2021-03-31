package com.sky.unionpay.pay.wechat;

import com.ijpay.core.enums.SignType;
import com.ijpay.core.enums.TradeType;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.model.UnifiedOrderModel;
import com.sky.unionpay.Exception.NoSuchPayChannelException;
import com.sky.unionpay.annotation.PaySource;
import com.sky.unionpay.constant.PayChannels;
import com.sky.unionpay.constant.PayStateEnum;
import com.sky.unionpay.constant.PayTypes;
import com.sky.unionpay.constant.StateEnum;
import com.sky.unionpay.model.*;
import com.sky.unionpay.model.merchant.Merchant;
import com.sky.unionpay.pay.IPay;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@PaySource(value = PayChannels.WECHAT, PayMethod = PayTypes.JSAPI)
public class JSAPIPay implements IPay {


    @Override
    public PayResult pay(PayRequestParam payRequestParam, Merchant merchant, PayOrder payOrder) {

        PayConfig payConfig = merchant.getPayConfig();
        // 统一下单构建请求参数
        Map<String, String> params = UnifiedOrderModel
                .builder()
                .appid(payConfig.getWechatAppId())
                .mch_id(payConfig.getWechatMerchantId())
                .nonce_str(WxPayKit.generateStr())
                .body("支付")
                .attach("聚合支付")
                .out_trade_no(payOrder.getId())
                .total_fee(payOrder.getAmount() + "")
                .spbill_create_ip("127.0.0.1")
                .notify_url(payOrder.getNoticeUrl())
                .trade_type(TradeType.JSAPI.getTradeType())
                .openid(payRequestParam.getOpenId())
                .build()
                .createSign(payConfig.getWechatKey(), SignType.HMACSHA256);
        // 发送请求
        String xmlResult = WxPayApi.pushOrder(false, params);
        // 将请求返回的 xml 数据转为 Map，方便后面逻辑获取数据
        Map<String, String> resultMap = WxPayKit.xmlToMap(xmlResult);
        // 判断返回的结果
        String returnCode = resultMap.get("return_code");
        String returnMsg = resultMap.get("return_msg");
        if (!WxPayKit.codeIsOk(returnCode)) {
            // 支付失败
            return buildPayResult(false, PayStateEnum.PAY_FAIL.getCode(), null);
        }
        String resultCode = resultMap.get("result_code");
        if (!WxPayKit.codeIsOk(resultCode)) {
            // 支付失败
            return buildPayResult(false, PayStateEnum.PAY_FAIL.getCode(), null);
        }

        // 以下字段在return_code 和result_code都为SUCCESS的时候有返回
        String prepayId = resultMap.get("prepay_id");
        // 二次签名，构建公众号唤起支付的参数,这里的签名方式要与上面统一下单请求签名方式保持一致
        Map<String, String> packageParams = WxPayKit.prepayIdCreateSign(prepayId, payConfig.getWechatAppId(), payConfig.getWechatKey(), SignType.HMACSHA256);

        return buildPayResult(true, PayStateEnum.PAYING.getCode(), packageParams);
    }

    private PayResult buildPayResult(boolean success, int payState, Object data) {
        PayResult payResult = new PayResult();
        payResult.setState(payState);
        if (success) {
            payResult.setSuccess(true);
            payResult.setData(data);
        } else {
            payResult.setSuccess(false);
        }
        return payResult;
    }
}
