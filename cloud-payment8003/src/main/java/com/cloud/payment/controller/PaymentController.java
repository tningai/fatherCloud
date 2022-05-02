package com.cloud.payment.controller;

import com.cloud.common.entities.CommonResult;
import com.cloud.common.entities.Payment;
import com.cloud.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Slf4j
public class PaymentController {

    @Resource(name = "paymentServiceImpl")
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPot;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);

        if (result > 0) {
            log.info("---插入成功---");
            return new CommonResult(200, "成功,serverPot:" + serverPot, result);
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
            return new CommonResult(200, "成功,serverPot:" + serverPot, payment);
        } else {
            return new CommonResult(444,"失败,id:" + id, null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element:services) {
            log.info("---element---" + element);
        }


        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("id:"+instance.getInstanceId()+",host:" + instance.getHost()+",port:"+instance.getPort()+",uri:" + instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/myPort")
    public String getPort(){
        System.out.println("hello git first edit");
        System.out.println("hello again git second edit");
        return serverPot;
    }
}
