package com.restaurantbackend.restaurantbackend.dto.table;

import com.restaurantbackend.restaurantbackend.entity.table.enums.TableStatus;
import lombok.Data;

@Data
public class TableUpdateDTO {
    private Integer tableNumber;
    private TableStatus status;
}