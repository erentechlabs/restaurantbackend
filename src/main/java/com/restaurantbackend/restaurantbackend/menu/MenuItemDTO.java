package com.restaurantbackend.restaurantbackend.menu;

import lombok.Data;

@Data
public class MenuItemDTO {
    private String name;
    private double price;
    private String description;
    private String categoryName;
}