package com.restaurantbackend.restaurantbackend.dto.menu;

import lombok.Data;

import java.util.List;

@Data
public class RestaurantDTO {
    private String name;
    private List<CategoryDTO> categories;
}
