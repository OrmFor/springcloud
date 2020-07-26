package com.atguigu.springcloud.services;


import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "----------------PaymentFallbackService fail back,paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "----------------PaymentFallbackService fail back,paymentInfo_TimeOut";
    }
}
