package com.pasan.paymentservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId; // Link to the order
    private String userId;  // User who made the payment
    private double amount;

    private String status; // PENDING, SUCCESS, FAILED
    private LocalDateTime paymentDate;

    private String paymentMethod; // e.g., CREDIT_CARD, PAYPAL
    private String transactionId; // External transaction reference
}
