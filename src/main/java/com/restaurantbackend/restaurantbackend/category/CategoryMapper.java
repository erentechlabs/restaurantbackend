package com.restaurantbackend.restaurantbackend.category;

import com.restaurantbackend.restaurantbackend.subcategory.SubCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final SubCategoryMapper subCategoryMapper;

    public CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());

        if (category.getSubCategories() != null) {
            dto.setSubCategories(
                    category.getSubCategories().stream()
                            .map(subCategoryMapper::toDTO)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public Category toEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }
}
