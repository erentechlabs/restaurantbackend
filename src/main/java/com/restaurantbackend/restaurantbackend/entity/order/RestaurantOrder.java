package com.restaurantbackend.restaurantbackend.entity.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.restaurantbackend.restaurantbackend.entity.menu.MenuItem;
import com.restaurantbackend.restaurantbackend.entity.session.TableSession;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@jakarta.persistence.Table(name = "orders")
public class RestaurantOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "orders_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    @JsonBackReference
    private TableSession session;

    private int quantity;

    private double totalPrice;

    private LocalDateTime orderedAt = LocalDateTime.now();
}