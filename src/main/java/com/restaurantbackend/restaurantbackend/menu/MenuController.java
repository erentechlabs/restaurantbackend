package com.restaurantbackend.restaurantbackend.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories/{categoryId}/subcategories/{subCategoryId}/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<List<MenuItemDTO>> getAllMenuItems(
            @PathVariable Long categoryId,
            @PathVariable Long subCategoryId) {
        List<MenuItemDTO> items = menuService.getAllMenuItems(categoryId, subCategoryId);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDTO> getMenuItemById(
            @PathVariable Long categoryId,
            @PathVariable Long subCategoryId,
            @PathVariable Long id) {
        MenuItemDTO item = menuService.getMenuItemById(categoryId, subCategoryId, id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<MenuItemDTO> addMenuItem(
            @PathVariable Long categoryId,
            @PathVariable Long subCategoryId,
            @RequestBody MenuItemDTO dto) {
        MenuItemDTO created = menuService.addMenuItem(categoryId, subCategoryId, dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemDTO> updateMenuItem(
            @PathVariable Long categoryId,
            @PathVariable Long subCategoryId,
            @PathVariable Long id,
            @RequestBody MenuItemDTO dto) {
        MenuItemDTO updated = menuService.updateMenuItem(categoryId, subCategoryId, id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(
            @PathVariable Long categoryId,
            @PathVariable Long subCategoryId,
            @PathVariable Long id) {
        menuService.deleteMenuItem(categoryId, subCategoryId, id);
        return ResponseEntity.noContent().build();
    }
}
