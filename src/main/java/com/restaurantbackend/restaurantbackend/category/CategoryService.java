package com.restaurantbackend.restaurantbackend.category;

import com.restaurantbackend.restaurantbackend.menu.MenuItem;
import com.restaurantbackend.restaurantbackend.menu.MenuItemDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Transactional
    public Category createCategory(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());

        if (dto.getMenuItems() != null) {
            List<MenuItem> menuItems = dto.getMenuItems().stream().map(menuItemDTO -> {
                MenuItem menuItem = new MenuItem();
                menuItem.setName(menuItemDTO.getName());
                menuItem.setPrice(menuItemDTO.getPrice());
                menuItem.setDescription(menuItemDTO.getDescription());
                menuItem.setCategory(category);
                return menuItem;
            }).collect(Collectors.toList());
            category.setMenuItems(menuItems);
        }

        return categoryRepository.save(category);
    }

    @Transactional
    public Optional<Category> updateCategory(Long id, CategoryDTO dto) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(dto.getName());

            category.getMenuItems().clear();
            if (dto.getMenuItems() != null) {
                List<MenuItem> menuItems = new ArrayList<>();
                for (MenuItemDTO itemDTO : dto.getMenuItems()) {
                    MenuItem menuItem = new MenuItem();
                    menuItem.setName(itemDTO.getName());
                    menuItem.setPrice(itemDTO.getPrice());
                    menuItem.setDescription(itemDTO.getDescription());
                    menuItem.setCategory(category);
                    MenuItem apply = menuItem;
                    menuItems.add(apply);
                }
                category.getMenuItems().addAll(menuItems);
            }

            return categoryRepository.save(category);
        });
    }

    public boolean deleteCategory(Long id) {
        return categoryRepository.findById(id).map(category -> {
            categoryRepository.delete(category);
            return true;
        }).orElse(false);
    }
}
