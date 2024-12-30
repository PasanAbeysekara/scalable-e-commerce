package com.pasan.paymentservice.service.impl;

import com.pasan.paymentservice.model.Payment;
import com.pasan.paymentservice.repository.PaymentRepository;
import com.pasan.paymentservice.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment processPayment(String userId, Payment payment) {
        payment.setUserId(userId);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus("PENDING");

        // Simulate external payment gateway interaction
        payment.setTransactionId("TXN" + System.currentTimeMillis());
        payment.setStatus("SUCCESS"); // Simulate a successful payment

        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentByOrderId(String orderId) {
        return paymentRepository.findByOrderId(orderId);
    }

    @Override
    public List<Payment> getUserPayments(String userId) {
        return paymentRepository.findByUserId(userId);
    }

    @Override
    public void updatePaymentStatus(Long paymentId, String status) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(status);
        paymentRepository.save(payment);
    }
}
