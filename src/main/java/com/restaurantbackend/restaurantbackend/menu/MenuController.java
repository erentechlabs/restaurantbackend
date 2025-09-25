package com.restaurantbackend.restaurantbackend.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/all")
    public List<MenuItemDTO> getAllMenuItems() {
        return menuService.getAllMenuItems();
    }

    @GetMapping("/getmenubyid/{id}")
    public MenuItemDTO getMenuItemById(@PathVariable Long id) {
        return menuService.getMenuItemById(id);
    }

    @PostMapping("/add")
    public MenuItemDTO addMenuItem(@RequestBody MenuItemDTO dto) {
        return menuService.addMenuItem(dto);
    }

    @PutMapping("/updatemenubyid/{id}")
    public MenuItemDTO updateMenuItem(@PathVariable Long id, @RequestBody MenuItemDTO dto) {
        return menuService.updateMenuItem(id, dto);
    }

    @DeleteMapping("/deletemenubyid/{id}")
    public void deleteMenuItem(@PathVariable Long id) {
        menuService.deleteMenuItem(id);
    }
}
