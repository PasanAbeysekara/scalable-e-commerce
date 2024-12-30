package com.pasan.orderservice.service.Impl;

import com.pasan.orderservice.dto.OrderDTO;
import com.pasan.orderservice.dto.OrderItemDTO;
import com.pasan.orderservice.model.Order;
import com.pasan.orderservice.model.OrderItem;
import com.pasan.orderservice.repository.OrderRepository;
import com.pasan.orderservice.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDTO createOrder(String userId, Order order) {
        order.setUserId(userId);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        order.getItems().forEach(item -> item.setOrder(order));
        Order savedOrder = orderRepository.save(order);

        return mapToOrderDTO(savedOrder);
    }

    @Override
    public OrderDTO getOrderById(Long id, String userId) {
        Order order = orderRepository.findById(id)
                .filter(o -> o.getUserId().equals(userId))
                .orElseThrow(() -> new RuntimeException("Order not found or access denied"));

        return mapToOrderDTO(order);
    }

    @Override
    public List<OrderDTO> getUserOrders(String userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(this::mapToOrderDTO).collect(Collectors.toList());
    }

    private OrderDTO mapToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUserId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setStatus(order.getStatus());

        List<OrderItemDTO> items = order.getItems().stream().map(item -> {
            OrderItemDTO itemDTO = new OrderItemDTO();
            itemDTO.setProductId(item.getProductId());
            itemDTO.setProductName(item.getProductName());
            itemDTO.setPrice(item.getPrice());
            itemDTO.setQuantity(item.getQuantity());
            return itemDTO;
        }).collect(Collectors.toList());

        orderDTO.setItems(items);
        return orderDTO;
    }

    @Override
    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
    }
}
