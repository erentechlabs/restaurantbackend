package com.restaurantbackend.restaurantbackend.mapper.menu;

import com.restaurantbackend.restaurantbackend.dto.menu.RestaurantDTO;
import com.restaurantbackend.restaurantbackend.dto.menu.RestaurantRequestDTO;
import com.restaurantbackend.restaurantbackend.entity.menu.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RestaurantMapper {

    private final CategoryMapper categoryMapper;

    public RestaurantDTO toDTO(Restaurant subcategory) {
        if (subcategory == null) return null;
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setName(subcategory.getName());

        if (subcategory.getCategories() != null) {
            restaurantDTO.setCategories(subcategory.getCategories()
                    .stream()
                    .map(categoryMapper::toDTO)
                    .collect(Collectors.toList()));
        }
        return restaurantDTO;
    }

    public Restaurant toEntity(RestaurantRequestDTO restaurantDTO) {
        if (restaurantDTO == null) return null;
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDTO.getName());
        return restaurant;
    }
}
