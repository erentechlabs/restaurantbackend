package com.restaurantbackend.restaurantbackend.service.menu;

import com.restaurantbackend.restaurantbackend.dto.menu.MenuItemDTO;
import com.restaurantbackend.restaurantbackend.dto.menu.MenuItemRequestDTO;
import com.restaurantbackend.restaurantbackend.entity.menu.MenuItem;
import com.restaurantbackend.restaurantbackend.entity.menu.Subcategory;
import com.restaurantbackend.restaurantbackend.mapper.menu.MenuItemMapper;
import com.restaurantbackend.restaurantbackend.repository.menu.MenuItemRepository;
import com.restaurantbackend.restaurantbackend.repository.menu.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;
    private final SubcategoryRepository subcategoryRepository;

    public MenuItemDTO createMenuItem(MenuItemRequestDTO menuItemRequestDTO) {
        Subcategory subcategory = subcategoryRepository.findById(menuItemRequestDTO.getSubcategoryId())
                .orElseThrow(() -> new RuntimeException("Subcategory not found with id: " + menuItemRequestDTO.getSubcategoryId()));

        MenuItem menuItem = menuItemMapper.toEntity(menuItemRequestDTO);
        menuItem.setSubcategory(subcategory);
        menuItem = menuItemRepository.save(menuItem);
        return menuItemMapper.toDTO(menuItem);
    }

    public MenuItemDTO getMenuItem(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem not found with id: " + id));
        return menuItemMapper.toDTO(menuItem);
    }

    public List<MenuItemDTO> getAllMenuItems() {
        return menuItemRepository.findAll().stream()
                .map(menuItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MenuItemDTO updateMenuItem(Long id, MenuItemRequestDTO menuItemRequestDTO) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem not found with id: " + id));

        Subcategory subcategory = subcategoryRepository.findById(menuItemRequestDTO.getSubcategoryId())
                .orElseThrow(() -> new RuntimeException("Subcategory not found with id: " + menuItemRequestDTO.getSubcategoryId()));

        menuItem.setName(menuItemRequestDTO.getName());
        menuItem.setPrice(menuItemRequestDTO.getPrice());
        menuItem.setDescription(menuItemRequestDTO.getDescription());
        menuItem.setAvailable(menuItemRequestDTO.isAvailable());
        menuItem.setImageUrl(menuItemRequestDTO.getImageUrl());
        menuItem.setSubcategory(subcategory);

        menuItem = menuItemRepository.save(menuItem);
        return menuItemMapper.toDTO(menuItem);
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
}