package com.restaurantbackend.restaurantbackend.service.menu;

import com.restaurantbackend.restaurantbackend.dto.menu.CategoryDTO;
import com.restaurantbackend.restaurantbackend.dto.menu.CategoryRequestDTO;
import com.restaurantbackend.restaurantbackend.entity.menu.Category;
import com.restaurantbackend.restaurantbackend.entity.menu.Restaurant;
import com.restaurantbackend.restaurantbackend.mapper.menu.CategoryMapper;
import com.restaurantbackend.restaurantbackend.repository.menu.CategoryRepository;
import com.restaurantbackend.restaurantbackend.repository.menu.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final RestaurantRepository restaurantRepository;

    public CategoryDTO createCategory(CategoryRequestDTO categoryRequestDTO) {

        Restaurant restaurant = restaurantRepository.findById(categoryRequestDTO.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + categoryRequestDTO.getRestaurantId()));

        Category category = categoryMapper.toEntity(categoryRequestDTO);
        category.setRestaurant(restaurant);
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
        Restaurant restaurant = restaurantRepository.findById(categoryRequestDTO.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + categoryRequestDTO.getRestaurantId()));

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        category.setName(categoryRequestDTO.getName());
        category.setRestaurant(restaurant);
        category = categoryRepository.save(category);
        return categoryMapper.toDTO(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}