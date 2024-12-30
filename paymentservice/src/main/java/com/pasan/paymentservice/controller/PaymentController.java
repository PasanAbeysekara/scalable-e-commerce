package com.pasan.paymentservice.controller;

import com.pasan.paymentservice.model.Payment;
import com.pasan.paymentservice.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> processPayment(@AuthenticationPrincipal Jwt jwt, @RequestBody Payment payment) {
        String userId = jwt.getSubject();
        return ResponseEntity.ok(paymentService.processPayment(userId, payment));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Payment> getPaymentByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok(paymentService.getPaymentByOrderId(orderId));
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getUserPayments(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        return ResponseEntity.ok(paymentService.getUserPayments(userId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updatePaymentStatus(@PathVariable Long id, @RequestParam String status) {
        paymentService.updatePaymentStatus(id, status);
        return ResponseEntity.noContent().build();
    }
}
