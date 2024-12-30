package com.pasan.orderservice.service;

import com.pasan.orderservice.model.Order;
import com.pasan.orderservice.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(String userId, Order order);
    OrderDTO getOrderById(Long id, String userId);
    List<OrderDTO> getUserOrders(String userId);
    void updateOrderStatus(Long orderId, String status);
}

