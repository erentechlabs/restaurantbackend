package com.restaurantbackend.restaurantbackend.dto.menu;

import lombok.Data;

@Data
public class MenuItemRequestDTO {
    private String name;
    private double price;
    private String description;
    private boolean isAvailable;
    private String imageUrl;
    private Long subcategoryId;
}