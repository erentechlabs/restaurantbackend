package com.restaurantbackend.restaurantbackend.subcategory;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories/{categoryId}/subcategories")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @GetMapping
    public ResponseEntity<List<SubCategoryDTO>> getAllSubCategories(@PathVariable Long categoryId) {
        List<SubCategoryDTO> subCategories = subCategoryService.getAllSubCategories(categoryId);
        return ResponseEntity.ok(subCategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategoryDTO> getSubCategoryById(@PathVariable Long categoryId,
                                                             @PathVariable Long id) {
        return subCategoryService.getSubCategoryById(categoryId, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<List<SubCategoryDTO>> createSubCategories(
            @PathVariable Long categoryId,
            @RequestBody List<SubCategoryDTO> dtos
    ) {

        dtos.forEach(dto -> dto.setMenuItems(null));
        List<SubCategoryDTO> created = subCategoryService.createSubCategories(categoryId, dtos);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategoryDTO> updateSubCategory(
            @PathVariable Long categoryId,
            @PathVariable Long id,
            @RequestBody SubCategoryDTO dto
    ) {

        dto.setMenuItems(null);
        return subCategoryService.updateSubCategory(categoryId, id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable Long categoryId,
                                                  @PathVariable Long id) {
        return subCategoryService.deleteSubCategory(categoryId, id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
