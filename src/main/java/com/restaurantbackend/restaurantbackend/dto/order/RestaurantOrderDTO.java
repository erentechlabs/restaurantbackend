package com.restaurantbackend.restaurantbackend.dto.order;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RestaurantOrderDTO {
    private Long id;
    private Long menuItemId;
    private String menuItemName;
    private double menuItemPrice;
    private int quantity;
    private double totalPrice;
    private LocalDateTime orderedAt;
    private Long sessionId;
}