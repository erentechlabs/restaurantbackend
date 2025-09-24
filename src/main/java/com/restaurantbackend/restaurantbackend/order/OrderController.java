package com.restaurantbackend.restaurantbackend.order;

import com.restaurantbackend.restaurantbackend.session.Session;
import com.restaurantbackend.restaurantbackend.session.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final SessionService sessionService;

    @PostMapping("/place/{sessionCode}")
    public Order placeOrder(@PathVariable String sessionCode, @RequestBody List<OrderItem> items) {
        Session session = sessionService.findByCode(sessionCode).orElseThrow();
        return orderService.placeOrder(session, items);
    }
}