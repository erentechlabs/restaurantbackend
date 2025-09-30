package com.restaurantbackend.restaurantbackend.dto.menu;

import lombok.Data;
import java.util.List;

@Data
public class SubcategoryDTO {
    private Long id;
    private String name;
    private Long categoryId;
    private List<MenuItemDTO> menuItems;
}