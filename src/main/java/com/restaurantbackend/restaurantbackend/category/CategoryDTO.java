
package com.restaurantbackend.restaurantbackend.category;

import com.restaurantbackend.restaurantbackend.subcategory.SubCategoryDTO;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private List<SubCategoryDTO> subCategories;
}
