package com.cloud.payment.dao;

import com.cloud.common.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment queryPaymentById(@Param("id") long id);
}
