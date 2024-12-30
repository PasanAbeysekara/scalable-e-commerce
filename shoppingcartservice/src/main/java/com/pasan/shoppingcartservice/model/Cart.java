package com.pasan.shoppingcartservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Data
@RedisHash("Cart") // Annotates the entity for Redis storage
public class Cart {

    @Id
    private String userId; // Unique identifier for the cart (mapped to userId)

    private List<CartItem> items; // List of items in the cart
}
