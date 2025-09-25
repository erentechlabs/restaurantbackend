package com.restaurantbackend.restaurantbackend.category;

import com.restaurantbackend.restaurantbackend.menu.MenuItemDTO;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private List<MenuItemDTO> menuItems;
}