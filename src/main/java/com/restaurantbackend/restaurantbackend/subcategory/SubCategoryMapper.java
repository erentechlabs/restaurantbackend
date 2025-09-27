package com.restaurantbackend.restaurantbackend.subcategory;
import com.restaurantbackend.restaurantbackend.menu.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubCategoryMapper {

    private final MenuMapper menuMapper;

    public SubCategoryDTO toDTO(SubCategory subCategory) {
        SubCategoryDTO dto = new SubCategoryDTO();
        dto.setId(subCategory.getId());
        dto.setName(subCategory.getName());

        if (subCategory.getMenuItems() != null) {
            dto.setMenuItems(
                    subCategory.getMenuItems().stream()
                            .map(menuMapper::toDTO)
                            .toList()
            );
        }

        return dto;
    }

    public SubCategory toEntity(SubCategoryDTO dto) {
        SubCategory subCategory = new SubCategory();
        if (dto.getId() != null) {
            subCategory.setId(dto.getId());
        }
        subCategory.setName(dto.getName());
        return subCategory;
    }
}
