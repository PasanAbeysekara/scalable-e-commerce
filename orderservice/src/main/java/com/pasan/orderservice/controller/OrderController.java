package com.pasan.orderservice.controller;

import com.pasan.orderservice.dto.OrderDTO;
import com.pasan.orderservice.model.Order;
import com.pasan.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@AuthenticationPrincipal Jwt jwt, @RequestBody Order order) {
        String userId = jwt.getSubject();
        return ResponseEntity.ok(orderService.createOrder(userId, order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        String userId = jwt.getSubject();
        return ResponseEntity.ok(orderService.getOrderById(id, userId));
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getUserOrders(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        orderService.updateOrderStatus(id, status);
        return ResponseEntity.noContent().build();
    }
}

