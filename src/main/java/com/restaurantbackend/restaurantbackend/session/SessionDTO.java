package com.restaurantbackend.restaurantbackend.session;

import com.restaurantbackend.restaurantbackend.order.OrderDTO;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SessionDTO {
    private String sessionCode;
    private LocalDateTime startTime;
    private boolean active;
    private Long tableId;
    private int tableNumber;
    private List<OrderDTO> orders;
}