package com.restaurantbackend.restaurantbackend.mapper.menu;

import com.restaurantbackend.restaurantbackend.dto.menu.MenuItemDTO;
import com.restaurantbackend.restaurantbackend.dto.menu.MenuItemRequestDTO;
import com.restaurantbackend.restaurantbackend.entity.menu.MenuItem;
import org.springframework.stereotype.Component;

@Component
public class MenuItemMapper {

    public MenuItemDTO toDTO(MenuItem menuItem) {
        if (menuItem == null) return null;

        MenuItemDTO dto = new MenuItemDTO();
        dto.setId(menuItem.getId());
        dto.setName(menuItem.getName());
        dto.setPrice(menuItem.getPrice());
        dto.setDescription(menuItem.getDescription());
        dto.setAvailable(menuItem.isAvailable());
        dto.setImageUrl(menuItem.getImageUrl());
        dto.setSubcategoryId(menuItem.getSubcategory() != null ? menuItem.getSubcategory().getId() : null);
        return dto;
    }

    public MenuItem toEntity(MenuItemRequestDTO dto) {
        if (dto == null) return null;

        MenuItem menuItem = new MenuItem();
        menuItem.setName(dto.getName());
        menuItem.setPrice(dto.getPrice());
        menuItem.setDescription(dto.getDescription());
        menuItem.setAvailable(dto.isAvailable());
        menuItem.setImageUrl(dto.getImageUrl());
        return menuItem;
    }
}