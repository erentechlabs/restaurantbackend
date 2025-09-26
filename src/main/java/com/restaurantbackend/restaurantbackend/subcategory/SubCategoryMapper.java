package com.restaurantbackend.restaurantbackend.subcategory;

import com.restaurantbackend.restaurantbackend.menu.MenuItemDTO;
import com.restaurantbackend.restaurantbackend.menu.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SubCategoryMapper {

    private final MenuMapper menuMapper;

    public SubCategoryDTO toDTO(SubCategory subCategory) {
        SubCategoryDTO dto = new SubCategoryDTO();
        dto.setId(subCategory.getId());
        dto.setName(subCategory.getName());

        if (subCategory.getMenuItems() != null) {
            List<MenuItemDTO> menuItems = subCategory.getMenuItems().stream()
                    .map(menuMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setMenuItems(menuItems);
        }

        return dto;
    }

    public SubCategory toEntity(SubCategoryDTO dto) {
        SubCategory subCategory = new SubCategory();

        if (dto.getId() != null) {
            subCategory.setId(dto.getId());
        }

        subCategory.setName(dto.getName());

        if (dto.getMenuItems() != null) {
            List<MenuItemDTO> items = dto.getMenuItems();
            List<com.restaurantbackend.restaurantbackend.menu.MenuItem> menuItems = items.stream().map(itemDTO -> {
                com.restaurantbackend.restaurantbackend.menu.MenuItem menuItem = new com.restaurantbackend.restaurantbackend.menu.MenuItem();
                menuItem.setName(itemDTO.getName());
                menuItem.setPrice(itemDTO.getPrice());
                menuItem.setDescription(itemDTO.getDescription());
                menuItem.setSubCategory(subCategory);
                return menuItem;
            }).collect(Collectors.toList());

            subCategory.setMenuItems(menuItems);
        }

        return subCategory;
    }
}
