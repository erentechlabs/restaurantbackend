package com.restaurantbackend.restaurantbackend.table;

import lombok.Data;

@Data
public class TableDTO {
    private Long id;
    private int number;
    private String nfcTagCode;
}
