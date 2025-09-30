package com.restaurantbackend.restaurantbackend.controller.menu;

import com.restaurantbackend.restaurantbackend.dto.menu.MenuItemDTO;
import com.restaurantbackend.restaurantbackend.dto.menu.MenuItemRequestDTO;
import com.restaurantbackend.restaurantbackend.service.menu.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @PostMapping
    public MenuItemDTO createMenuItem(@RequestBody MenuItemRequestDTO menuItemRequestDTO) {
        return menuItemService.createMenuItem(menuItemRequestDTO);
    }

    @GetMapping("/{id}")
    public MenuItemDTO getMenuItem(@PathVariable Long id) {
        return menuItemService.getMenuItem(id);
    }

    @GetMapping
    public List<MenuItemDTO> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @PutMapping("/{id}")
    public MenuItemDTO updateMenuItem(@PathVariable Long id, @RequestBody MenuItemRequestDTO menuItemRequestDTO) {
        return menuItemService.updateMenuItem(id, menuItemRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
    }
}