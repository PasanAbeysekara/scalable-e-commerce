package com.pasan.shoppingcartservice.service.Impl;

import com.pasan.shoppingcartservice.model.Cart;
import com.pasan.shoppingcartservice.model.CartItem;
import com.pasan.shoppingcartservice.repository.CartRepository;
import com.pasan.shoppingcartservice.service.CartService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart addItemToCart(String userId, CartItem item) {
        Cart cart = cartRepository.findById(userId).orElse(new Cart());
        cart.setUserId(userId);

        List<CartItem> items = cart.getItems() != null ? cart.getItems() : new ArrayList<>();
        items.removeIf(i -> i.getProductId().equals(item.getProductId()));
        items.add(item);

        cart.setItems(items);
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCart(String userId) {
        return cartRepository.findById(userId).orElse(new Cart());
    }

    @Override
    public Cart removeItemFromCart(String userId, Long productId) {
        Cart cart = cartRepository.findById(userId).orElseThrow(() -> new RuntimeException("Cart not found"));
        List<CartItem> items = cart.getItems();
        items.removeIf(i -> i.getProductId().equals(productId));
        cart.setItems(items);
        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(String userId) {
        cartRepository.deleteById(userId);
    }

    @PostConstruct
        public void testRedisConnection() {
            System.out.println("Testing Redis Connection...");
        Cart testCart = new Cart();
        testCart.setUserId("test-user");
        testCart.setItems(new ArrayList<>());
        cartRepository.save(testCart);

        Cart retrievedCart = cartRepository.findById("test-user").orElse(null);
        System.out.println("Retrieved Cart: " + retrievedCart);
    }

}

