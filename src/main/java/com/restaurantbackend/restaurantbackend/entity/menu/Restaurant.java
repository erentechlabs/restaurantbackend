package com.restaurantbackend.restaurantbackend.entity.menu;


import com.restaurantbackend.restaurantbackend.entity.table.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@jakarta.persistence.Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Category> categories;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Table> tables;
}