package com.pasan.orderservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private String userId;
    private LocalDateTime orderDate;
    private String status;
    private List<OrderItemDTO> items;
}
