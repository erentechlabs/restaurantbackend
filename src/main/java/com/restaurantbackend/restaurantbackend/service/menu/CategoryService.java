package com.restaurantbackend.restaurantbackend.service.menu;

import com.restaurantbackend.restaurantbackend.dto.menu.CategoryDTO;
import com.restaurantbackend.restaurantbackend.dto.menu.CategoryRequestDTO;
import com.restaurantbackend.restaurantbackend.entity.menu.Category;
import com.restaurantbackend.restaurantbackend.mapper.menu.CategoryMapper;
import com.restaurantbackend.restaurantbackend.repository.menu.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryMapper.toEntity(categoryRequestDTO);
        category = categoryRepository.save(category);
        return categoryMapper.toDTO(category);
    }

    public CategoryDTO getCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return categoryMapper.toDTO(category);
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        category.setName(categoryRequestDTO.getName());
        category = categoryRepository.save(category);
        return categoryMapper.toDTO(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}