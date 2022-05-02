package com.cloud.payment.service.impl;

import com.cloud.common.entities.Payment;
import com.cloud.payment.dao.PaymentDao;
import com.cloud.payment.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment queryPaymentById(long id) {
        return paymentDao.queryPaymentById(id);
    }
}
