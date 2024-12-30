package com.pasan.shoppingcartservice.controller;

import com.pasan.shoppingcartservice.model.Cart;
import com.pasan.shoppingcartservice.model.CartItem;
import com.pasan.shoppingcartservice.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Cart> addItemToCart(@AuthenticationPrincipal Jwt jwt, @RequestBody CartItem item) {
        String userId = jwt.getSubject(); // Extract user ID from JWT
        return ResponseEntity.ok(cartService.addItemToCart(userId, item));
    }

    @GetMapping
    public ResponseEntity<Cart> getCart(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Cart> removeItemFromCart(@AuthenticationPrincipal Jwt jwt, @PathVariable Long productId) {
        String userId = jwt.getSubject();
        return ResponseEntity.ok(cartService.removeItemFromCart(userId, productId));
    }

    @DeleteMapping
    public ResponseEntity<Void> clearCart(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
