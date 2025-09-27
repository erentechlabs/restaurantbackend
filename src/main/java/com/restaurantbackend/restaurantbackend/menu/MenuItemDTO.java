package com.restaurantbackend.restaurantbackend.menu;

import lombok.Data;

@Data
public class MenuItemDTO {
    private Long id;
    private String name;
    private double price;
    private String description;
    private boolean isAvailable;
    private String imageUrl;
}