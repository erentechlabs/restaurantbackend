package com.restaurantbackend.restaurantbackend.dto.menu;

import lombok.Data;
import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private List<SubcategoryDTO> subcategories;
}