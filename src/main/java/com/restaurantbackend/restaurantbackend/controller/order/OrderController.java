package com.restaurantbackend.restaurantbackend.controller.order;

import com.restaurantbackend.restaurantbackend.dto.order.RestaurantOrderDTO;
import com.restaurantbackend.restaurantbackend.dto.order.AddOrderDTO;
import com.restaurantbackend.restaurantbackend.service.order.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/sessions/{sessionId}")
    public ResponseEntity<RestaurantOrderDTO> addOrder(@PathVariable Long sessionId, @RequestBody AddOrderDTO dto) {
        try {
            RestaurantOrderDTO orderDTO = orderService.addOrderToSession(sessionId, dto);
            return ResponseEntity.ok(orderDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/sessions/{sessionId}")
    public ResponseEntity<List<RestaurantOrderDTO>> getOrders(@PathVariable Long sessionId) {
        try {
            return ResponseEntity.ok(orderService.getOrdersForSession(sessionId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}