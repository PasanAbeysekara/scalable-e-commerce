package com.pasan.shoppingcartservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
}
