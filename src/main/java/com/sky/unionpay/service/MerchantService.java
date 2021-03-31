package com.sky.unionpay.service;

import com.sky.unionpay.Exception.GeneralException;
import com.sky.unionpay.constant.StateEnum;
import com.sky.unionpay.model.merchant.Merchant;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

    public Merchant findById(String id){

        return new Merchant();
    }


    public Merchant findEnableMerchant(String id){
        Merchant merchant = new Merchant();


        return merchant;
    }
}
