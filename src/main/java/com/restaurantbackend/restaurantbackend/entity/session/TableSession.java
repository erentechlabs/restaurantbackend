package com.restaurantbackend.restaurantbackend.entity.session;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.restaurantbackend.restaurantbackend.entity.order.RestaurantOrder;
import com.restaurantbackend.restaurantbackend.entity.table.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@jakarta.persistence.Table(name = "table_session")
public class TableSession {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session_seq")
    @SequenceGenerator(name = "session_seq", sequenceName = "table_session_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String password;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private boolean active = true;

    @OneToOne
    @JoinColumn(name = "table_id", nullable = false, unique = true)
    private Table table;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<RestaurantOrder> orders = new ArrayList<>();
}