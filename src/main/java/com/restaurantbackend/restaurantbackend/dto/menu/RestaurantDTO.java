package com.restaurantbackend.restaurantbackend.dto.menu;

import com.restaurantbackend.restaurantbackend.dto.table.TableDTO;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantDTO {
    private String name;
    private List<CategoryDTO> categories;
    private List<TableDTO> tables;
}
