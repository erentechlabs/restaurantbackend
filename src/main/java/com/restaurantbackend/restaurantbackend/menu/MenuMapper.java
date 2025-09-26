package com.restaurantbackend.restaurantbackend.menu;

import org.springframework.stereotype.Component;

@Component
public class MenuMapper {

    public MenuItemDTO toDTO(MenuItem menuItem) {
        MenuItemDTO dto = new MenuItemDTO();
        dto.setId(menuItem.getId());
        dto.setName(menuItem.getName());
        dto.setPrice(menuItem.getPrice());
        dto.setDescription(menuItem.getDescription());
        dto.setSubCategoryName(menuItem.getSubCategory() != null ? menuItem.getSubCategory().getName() : null);
        return dto;
    }

    public MenuItem toEntity(MenuItemDTO dto) {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(dto.getId());
        menuItem.setName(dto.getName());
        menuItem.setPrice(dto.getPrice());
        menuItem.setDescription(dto.getDescription());
        return menuItem;
    }
}
