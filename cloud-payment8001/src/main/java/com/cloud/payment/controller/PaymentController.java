package com.cloud.payment.controller;

import com.cloud.common.entities.CommonResult;
import com.cloud.common.entities.Payment;
import com.cloud.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource(name = "paymentServiceImpl")
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);

        if (result > 0) {
            log.info("---插入成功---");
            return new CommonResult(200, "成功.serverPort:" + serverPort, result);
        } else {
            log.info("---插入失败---");
            return new CommonResult(444,"失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult query(@PathVariable("id") Long id){
        Payment payment = paymentService.queryPaymentById(id);
        if (payment != null) {
            log.info(payment.toString());
            return new CommonResult(200, "成功.serverPort:" + serverPort, payment);
        } else {
            return new CommonResult(444,"失败,id:" + id, null);
        }
    }
}
