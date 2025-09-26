package com.restaurantbackend.restaurantbackend.menu;

import com.restaurantbackend.restaurantbackend.subcategory.SubCategory;
import com.restaurantbackend.restaurantbackend.subcategory.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final MenuMapper menuMapper;

    public List<MenuItemDTO> getAllMenuItems() {
        return menuRepository.findAll().stream()
                .map(menuMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MenuItemDTO getMenuItemById(Long id) {
        return menuRepository.findById(id)
                .map(menuMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("MenuItem not found: " + id));
    }

    public MenuItemDTO addMenuItem(MenuItemDTO dto) {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(dto.getName());
        menuItem.setPrice(dto.getPrice());
        menuItem.setDescription(dto.getDescription());

        if (dto.getSubCategoryName() != null) {
            SubCategory subCategory = subCategoryRepository.findByName(dto.getSubCategoryName())
                    .orElseGet(() -> {
                        SubCategory newCat = new SubCategory();
                        newCat.setName(dto.getSubCategoryName());
                        return subCategoryRepository.save(newCat);
                    });
            menuItem.setSubCategory(subCategory);
        }

        MenuItem saved = menuRepository.save(menuItem);
        return menuMapper.toDTO(saved);
    }

    public MenuItemDTO updateMenuItem(Long id, MenuItemDTO dto) {
        MenuItem existing = menuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("MenuItem not found: " + id));

        if (dto.getName() != null) existing.setName(dto.getName());
        if (dto.getDescription() != null) existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());

        if (dto.getSubCategoryName() != null) {
            SubCategory subCategory = subCategoryRepository.findByName(dto.getSubCategoryName())
                    .orElseGet(() -> {
                        SubCategory newCat = new SubCategory();
                        newCat.setName(dto.getSubCategoryName());
                        return subCategoryRepository.save(newCat);
                    });
            existing.setSubCategory(subCategory);
        }

        MenuItem saved = menuRepository.save(existing);
        return menuMapper.toDTO(saved);
    }

    public void deleteMenuItem(Long id) {
        menuRepository.deleteById(id);
    }
}
