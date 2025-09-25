package com.restaurantbackend.restaurantbackend.menu;

import lombok.Data;

@Data
public class MenuItemDTO {
    private Long id;
    private String name;
    private double price;
    private String description;
    private String categoryName;
}