package com.restaurantbackend.restaurantbackend.order;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private LocalDateTime orderTime;
    private List<OrderItemDTO> items;
}