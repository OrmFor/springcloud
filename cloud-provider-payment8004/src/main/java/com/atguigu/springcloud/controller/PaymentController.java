package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort ;

    @Autowired
    private PaymentService paymentService;


    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
       int result = paymentService.create(payment);
       if(result > 0){
           log.info(MessageFormat.format("***********{0}***************",serverPort));

           return new CommonResult(200,"插入数据库成功,serverPort:"+serverPort,result);
       }else{
           return new CommonResult(400,"插入数据库失败，serverPort："+serverPort,null);

       }
    }


    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment result = paymentService.getPaymentById(id);
        if(result != null){
            log.info(MessageFormat.format("***********{0}***************",serverPort));

            return new CommonResult(200,"查询成功，serverPort："+serverPort,result);
        }else{
            return new CommonResult(400,"没有对应记录，查询ID:"+id,null);

        }
    }
}
