package com.pasan.orderservice.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
}

