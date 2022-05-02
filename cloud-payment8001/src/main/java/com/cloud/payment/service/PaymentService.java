package com.cloud.payment.service;

import com.cloud.common.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    int create(Payment payment);

    Payment queryPaymentById(@Param("id") long id);
}
