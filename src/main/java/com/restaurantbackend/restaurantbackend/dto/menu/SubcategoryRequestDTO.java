package com.restaurantbackend.restaurantbackend.dto.menu;

import lombok.Data;

@Data
public class SubcategoryRequestDTO {
    private String name;
    private Long categoryId;
}