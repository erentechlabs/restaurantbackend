package com.restaurantbackend.restaurantbackend.order;

import lombok.Data;

@Data
public class OrderItemDTO {
    private String menuItemName;
    private int quantity;
}
