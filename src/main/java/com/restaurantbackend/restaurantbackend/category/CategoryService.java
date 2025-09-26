package com.restaurantbackend.restaurantbackend.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public CategoryDTO addCategory(CategoryDTO dto) {
        Category category = categoryMapper.toEntity(dto);

        // SubCategory'lerin parent Category ayarı
        if (category.getSubCategories() != null) {
            category.getSubCategories().forEach(sub -> sub.setCategory(category));
        }

        Category saved = categoryRepository.save(category);
        return categoryMapper.toDTO(saved);
    }
}
