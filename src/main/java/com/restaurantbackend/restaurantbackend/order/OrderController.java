package com.restaurantbackend.restaurantbackend.order;

import com.restaurantbackend.restaurantbackend.session.Session;
import com.restaurantbackend.restaurantbackend.session.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final SessionService sessionService;

    @PostMapping("table/{sessionCode}")
    public OrderDTO placeOrder(@PathVariable String sessionCode, @RequestBody List<OrderItemDTO> items) {
        Session session = sessionService.findByCode(sessionCode)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));
        return orderService.placeOrder(session, items);
    }
}
