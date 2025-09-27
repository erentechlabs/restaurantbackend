package com.restaurantbackend.restaurantbackend.menu;

import com.restaurantbackend.restaurantbackend.subcategory.SubCategory;
import com.restaurantbackend.restaurantbackend.subcategory.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final MenuMapper menuMapper;

    public List<MenuItemDTO> getAllMenuItems(Long categoryId, Long subCategoryId) {
        SubCategory subCategory = subCategoryRepository.findById(subCategoryId)
                .filter(sub -> sub.getCategory().getId().equals(categoryId))
                .orElseThrow(() -> new IllegalArgumentException(
                        "SubCategory not found for categoryId=" + categoryId + " and subCategoryId=" + subCategoryId));

        return subCategory.getMenuItems().stream()
                .map(menuMapper::toDTO)
                .toList();
    }

    public MenuItemDTO getMenuItemById(Long categoryId, Long subCategoryId, Long id) {
        return menuRepository.findById(id)
                .filter(item -> item.getSubCategory().getId().equals(subCategoryId)
                        && item.getSubCategory().getCategory().getId().equals(categoryId))
                .map(menuMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException(
                        "MenuItem not found for categoryId=" + categoryId +
                                ", subCategoryId=" + subCategoryId + ", id=" + id));
    }

    public MenuItemDTO addMenuItem(Long categoryId, Long subCategoryId, MenuItemDTO dto) {
        SubCategory subCategory = subCategoryRepository.findById(subCategoryId)
                .filter(sub -> sub.getCategory().getId().equals(categoryId))
                .orElseThrow(() -> new IllegalArgumentException(
                        "SubCategory not found for categoryId=" + categoryId + " and subCategoryId=" + subCategoryId));

        MenuItem menuItem = menuMapper.toEntity(dto);
        menuItem.setSubCategory(subCategory);

        MenuItem saved = menuRepository.save(menuItem);
        return menuMapper.toDTO(saved);
    }

    public MenuItemDTO updateMenuItem(Long categoryId, Long subCategoryId, Long id, MenuItemDTO dto) {
        MenuItem existing = menuRepository.findById(id)
                .filter(item -> item.getSubCategory().getId().equals(subCategoryId)
                        && item.getSubCategory().getCategory().getId().equals(categoryId))
                .orElseThrow(() -> new IllegalArgumentException(
                        "MenuItem not found for categoryId=" + categoryId +
                                ", subCategoryId=" + subCategoryId + ", id=" + id));

        if (dto.getName() != null) existing.setName(dto.getName());
        if (dto.getDescription() != null) existing.setDescription(dto.getDescription());
        if (dto.getPrice() != 0) existing.setPrice(dto.getPrice());
        existing.setAvailable(dto.isAvailable());
        if (dto.getImageUrl() != null) existing.setImageUrl(dto.getImageUrl());

        MenuItem saved = menuRepository.save(existing);
        return menuMapper.toDTO(saved);
    }

    public void deleteMenuItem(Long categoryId, Long subCategoryId, Long id) {
        MenuItem existing = menuRepository.findById(id)
                .filter(item -> item.getSubCategory().getId().equals(subCategoryId)
                        && item.getSubCategory().getCategory().getId().equals(categoryId))
                .orElseThrow(() -> new IllegalArgumentException(
                        "MenuItem not found for categoryId=" + categoryId +
                                ", subCategoryId=" + subCategoryId + ", id=" + id));

        menuRepository.delete(existing);
    }
}
