package com.restaurantbackend.restaurantbackend.menu;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurantbackend.restaurantbackend.subcategory.SubCategory;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@jakarta.persistence.Table(name = "menu_item")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    @JsonIgnore
    private SubCategory subCategory;
}