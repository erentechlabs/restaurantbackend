package com.restaurantbackend.restaurantbackend.service.menu;

import com.restaurantbackend.restaurantbackend.dto.menu.RestaurantDTO;
import com.restaurantbackend.restaurantbackend.dto.menu.RestaurantRequestDTO;
import com.restaurantbackend.restaurantbackend.entity.menu.Restaurant;
import com.restaurantbackend.restaurantbackend.mapper.menu.RestaurantMapper;
import com.restaurantbackend.restaurantbackend.repository.menu.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantDTO createRestaurant(RestaurantRequestDTO restaurantRequestDTO) {
        return restaurantMapper.toDTO(restaurantRepository.save(restaurantMapper.toEntity(restaurantRequestDTO)));
    }

    public RestaurantDTO updateRestaurant(Long id ,RestaurantRequestDTO restaurantRequestDTO) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
        restaurant.setName(restaurantRequestDTO.getName());
        restaurantRepository.save(restaurant);
        return restaurantMapper.toDTO(restaurant);
    }

    public RestaurantDTO getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
        return restaurantMapper.toDTO(restaurant);
    }

    public void deleteRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
        restaurantRepository.deleteById(id);
    }

}
