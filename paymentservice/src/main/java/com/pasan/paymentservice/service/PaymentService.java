package com.pasan.paymentservice.service;

import com.pasan.paymentservice.model.Payment;

import java.util.List;

public interface PaymentService {
    Payment processPayment(String userId, Payment payment);
    Payment getPaymentByOrderId(String orderId);
    List<Payment> getUserPayments(String userId);
    void updatePaymentStatus(Long paymentId, String status);
}
