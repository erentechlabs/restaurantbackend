package com.restaurantbackend.restaurantbackend.subcategory;

import com.restaurantbackend.restaurantbackend.category.Category;
import com.restaurantbackend.restaurantbackend.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryMapper subCategoryMapper;

    @Transactional
    public List<SubCategoryDTO> createSubCategories(Long categoryId, List<SubCategoryDTO> dtos) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found: " + categoryId));

        return dtos.stream()
                .map(dto -> {
                    SubCategory subCategory = subCategoryMapper.toEntity(dto);
                    subCategory.setCategory(category);
                    SubCategory saved = subCategoryRepository.save(subCategory);
                    return subCategoryMapper.toDTO(saved);
                })
                .toList();
    }

    public List<SubCategoryDTO> getAllSubCategories(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found: " + categoryId));
        return category.getSubCategories().stream()
                .map(subCategoryMapper::toDTO)
                .toList();
    }

    public Optional<SubCategoryDTO> getSubCategoryById(Long categoryId, Long id) {
        return subCategoryRepository.findById(id)
                .filter(sub -> sub.getCategory().getId().equals(categoryId))
                .map(subCategoryMapper::toDTO);
    }

    @Transactional
    public Optional<SubCategoryDTO> updateSubCategory(Long categoryId, Long id, SubCategoryDTO dto) {
        return subCategoryRepository.findById(id)
                .filter(sub -> sub.getCategory().getId().equals(categoryId))
                .map(existing -> {
                    existing.setName(dto.getName());
                    SubCategory saved = subCategoryRepository.save(existing);
                    return subCategoryMapper.toDTO(saved);
                });
    }

    @Transactional
    public boolean deleteSubCategory(Long categoryId, Long id) {
        return subCategoryRepository.findById(id)
                .filter(sub -> sub.getCategory().getId().equals(categoryId))
                .map(sub -> {
                    subCategoryRepository.delete(sub);
                    return true;
                })
                .orElse(false);
    }
}
