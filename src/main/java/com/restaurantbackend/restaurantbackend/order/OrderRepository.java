package com.restaurantbackend.restaurantbackend.order;

import com.restaurantbackend.restaurantbackend.session.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findBySession(Session session);
}