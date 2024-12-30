package com.pasan.paymentservice.repository;

import com.pasan.paymentservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUserId(String userId);
    Payment findByOrderId(String orderId);
}
