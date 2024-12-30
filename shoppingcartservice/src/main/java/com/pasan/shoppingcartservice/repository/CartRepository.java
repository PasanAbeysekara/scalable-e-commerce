package com.pasan.shoppingcartservice.repository;

import com.pasan.shoppingcartservice.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, String> {
    // No additional methods are required for basic CRUD operations
}
