package com.restaurantbackend.restaurantbackend.config.entity;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ApiError {
    private int status;
    private String error;
    private String message;
    private String path;
    private Instant timestamp;
}
