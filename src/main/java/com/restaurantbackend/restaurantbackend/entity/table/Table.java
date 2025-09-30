package com.restaurantbackend.restaurantbackend.entity.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurantbackend.restaurantbackend.entity.session.TableSession;
import com.restaurantbackend.restaurantbackend.entity.table.enums.TableStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@jakarta.persistence.Table(name = "restaurant_table")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "table_seq")
    @SequenceGenerator(name = "table_seq", sequenceName = "restaurant_table_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "table_number", unique = true)
    private Integer tableNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TableStatus status = TableStatus.FREE;

    @Column(name = "next_password", nullable = false)
    private String nextPassword;

    @OneToOne(mappedBy = "table")
    @JsonIgnore
    private TableSession currentSession;
}