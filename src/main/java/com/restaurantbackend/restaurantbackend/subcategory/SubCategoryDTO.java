package com.restaurantbackend.restaurantbackend.subcategory;

import com.restaurantbackend.restaurantbackend.menu.MenuItemDTO;
import lombok.Data;

import java.util.List;

@Data
public class SubCategoryDTO {
    private Long id;
    private String name;
    private List<MenuItemDTO> menuItems;
}