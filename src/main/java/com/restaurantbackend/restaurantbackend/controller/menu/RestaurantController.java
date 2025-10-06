package com.restaurantbackend.restaurantbackend.controller.menu;
import com.restaurantbackend.restaurantbackend.dto.menu.RestaurantDTO;
import com.restaurantbackend.restaurantbackend.dto.menu.RestaurantRequestDTO;
import com.restaurantbackend.restaurantbackend.service.menu.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody RestaurantRequestDTO restaurantRequestDTO) {
        RestaurantDTO created = restaurantService.createRestaurant(restaurantRequestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantRequestDTO restaurantRequestDTO) {
        restaurantService.updateRestaurant(id, restaurantRequestDTO);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable Long id) {
        RestaurantDTO restaurantDTO = restaurantService.getRestaurant(id);
        return ResponseEntity.ok(restaurantDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}
