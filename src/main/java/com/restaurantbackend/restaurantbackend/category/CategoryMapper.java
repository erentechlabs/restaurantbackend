package com.restaurantbackend.restaurantbackend.category;

import com.restaurantbackend.restaurantbackend.menu.MenuItemDTO;
import com.restaurantbackend.restaurantbackend.menu.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final MenuMapper menuMapper;

    public CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());

        if (category.getMenuItems() != null) {
            List<MenuItemDTO> menuItems = category.getMenuItems().stream()
                    .map(menuMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setMenuItems(menuItems);
        }

        return dto;
    }

    public Category toEntity(CategoryDTO dto) {
        Category category = new Category();

        if (dto.getId() != null) {
            category.setId(dto.getId());
        }

        category.setName(dto.getName());

        if (dto.getMenuItems() != null) {
            List<MenuItemDTO> items = dto.getMenuItems();
            List<com.restaurantbackend.restaurantbackend.menu.MenuItem> menuItems = items.stream().map(itemDTO -> {
                com.restaurantbackend.restaurantbackend.menu.MenuItem menuItem = new com.restaurantbackend.restaurantbackend.menu.MenuItem();
                menuItem.setName(itemDTO.getName());
                menuItem.setPrice(itemDTO.getPrice());
                menuItem.setDescription(itemDTO.getDescription());
                menuItem.setCategory(category);
                return menuItem;
            }).collect(Collectors.toList());

            category.setMenuItems(menuItems);
        }

        return category;
    }
}
