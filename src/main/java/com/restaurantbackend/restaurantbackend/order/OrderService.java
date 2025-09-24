package com.restaurantbackend.restaurantbackend.order;

import com.restaurantbackend.restaurantbackend.session.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order placeOrder(Session session, java.util.List<OrderItem> items) {
        Order order = new Order();
        order.setSession(session);
        order.setOrderTime(LocalDateTime.now());
        // ensure back-references
        for (OrderItem item : items) {
            item.setOrder(order);
        }
        order.setItems(items);
        return orderRepository.save(order);
    }
}