package com.restaurantbackend.restaurantbackend.dto.table;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TableSessionDTO {
    private Long id;
    private String password;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean active;
    private Long tableId;
}