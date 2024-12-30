package com.pasan.shoppingcartservice.service;

import com.pasan.shoppingcartservice.model.Cart;
import com.pasan.shoppingcartservice.model.CartItem;

public interface CartService {
    Cart addItemToCart(String userId, CartItem item);
    Cart getCart(String userId);
    Cart removeItemFromCart(String userId, Long productId);
    void clearCart(String userId);
}

