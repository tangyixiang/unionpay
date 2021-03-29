package com.sky.unionpay.service;

import com.sky.unionpay.model.merchant.Merchant;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

    public Merchant findById(String id){

        return new Merchant();
    }
}
