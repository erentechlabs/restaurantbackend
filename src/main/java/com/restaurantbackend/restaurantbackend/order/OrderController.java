package com.restaurantbackend.restaurantbackend.order;

import com.restaurantbackend.restaurantbackend.session.Session;
import com.restaurantbackend.restaurantbackend.session.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders/table")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final SessionService sessionService;

    @PostMapping("/{sessionCode}")
    public ResponseEntity<OrderDTO> tableOrder(@PathVariable String sessionCode, @RequestBody List<OrderItemDTO> items) {
        Session session = sessionService.findByCode(sessionCode)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));
        OrderDTO order = orderService.placeOrder(session, items);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{sessionCode}")
    public ResponseEntity<OrderDTO> addItemsToOrder(@PathVariable String sessionCode, @RequestBody List<OrderItemDTO> items) {
        Session session = sessionService.findByCode(sessionCode)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));
        OrderDTO order = orderService.updateOrder(session, items);
        return ResponseEntity.ok(order);
    }


    @PutMapping("/{orderId}/item/{oldItemName}")
    public ResponseEntity<OrderDTO> updateOrderItem(
            @PathVariable Long orderId,
            @PathVariable String oldItemName,
            @RequestBody OrderItemDTO newItem
    ) {
        OrderDTO order = orderService.updateOrderItem(orderId, oldItemName, newItem);
        return ResponseEntity.ok(order);
    }
}
