package com.restaurantbackend.restaurantbackend.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDTO)
                .toList();
    }

    public Optional<CategoryDTO> getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDTO);
    }

    @Transactional
    public CategoryDTO createCategory(CategoryDTO dto) {
        Category category = categoryMapper.toEntity(dto);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toDTO(saved);
    }

    @Transactional
    public Optional<CategoryDTO> updateCategory(Long id, CategoryDTO dto) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(dto.getName());

            category.getMenuItems().clear();

            if (dto.getMenuItems() != null) {
                List<com.restaurantbackend.restaurantbackend.menu.MenuItem> menuItems = dto.getMenuItems().stream()
                        .map(itemDTO -> {
                            com.restaurantbackend.restaurantbackend.menu.MenuItem menuItem = new com.restaurantbackend.restaurantbackend.menu.MenuItem();
                            menuItem.setName(itemDTO.getName());
                            menuItem.setPrice(itemDTO.getPrice());
                            menuItem.setDescription(itemDTO.getDescription());
                            menuItem.setCategory(category);
                            return menuItem;
                        }).toList();

                category.getMenuItems().addAll(menuItems);
            }

            Category saved = categoryRepository.save(category);
            return categoryMapper.toDTO(saved);
        });
    }


    public boolean deleteCategory(Long id) {
        return categoryRepository.findById(id).map(category -> {
            categoryRepository.delete(category);
            return true;
        }).orElse(false);
    }
}
