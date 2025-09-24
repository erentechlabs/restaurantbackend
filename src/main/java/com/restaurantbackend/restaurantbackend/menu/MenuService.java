package com.restaurantbackend.restaurantbackend.menu;

import com.restaurantbackend.restaurantbackend.category.Category;
import com.restaurantbackend.restaurantbackend.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    public List<MenuItemDTO> getAllMenuItems() {
        return menuRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MenuItemDTO convertToDTO(MenuItem menuItem) {
        MenuItemDTO dto = new MenuItemDTO();
        dto.setName(menuItem.getName());
        dto.setPrice(menuItem.getPrice());
        dto.setDescription(menuItem.getDescription());
        dto.setCategoryName(menuItem.getCategory() != null ? menuItem.getCategory().getName() : null);
        return dto;
    }

    public MenuItemDTO addMenuItem(MenuItemDTO dto) {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(dto.getName());
        menuItem.setPrice(dto.getPrice());
        menuItem.setDescription(dto.getDescription());

        if (dto.getCategoryName() != null) {
            Category category = categoryRepository.findByName(dto.getCategoryName())
                    .orElseGet(() -> {
                        Category newCat = new Category();
                        newCat.setName(dto.getCategoryName());
                        return categoryRepository.save(newCat);
                    });
            menuItem.setCategory(category);
        }

        MenuItem saved = menuRepository.save(menuItem);
        return convertToDTO(saved);
    }

    public MenuItemDTO updateMenuItem(Long id, MenuItemDTO dto) {
        MenuItem existing = menuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("MenuItem not found: " + id));

        if (dto.getName() != null) existing.setName(dto.getName());
        if (dto.getDescription() != null) existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());

        if (dto.getCategoryName() != null) {
            Category category = categoryRepository.findByName(dto.getCategoryName())
                    .orElseGet(() -> {
                        Category newCat = new Category();
                        newCat.setName(dto.getCategoryName());
                        return categoryRepository.save(newCat);
                    });
            existing.setCategory(category);
        }

        MenuItem saved = menuRepository.save(existing);
        return convertToDTO(saved);
    }

    public void deleteMenuItem(Long id) {
        menuRepository.deleteById(id);
    }
}
