package com.restaurantbackend.restaurantbackend.subcategory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurantbackend.restaurantbackend.category.Category;
import com.restaurantbackend.restaurantbackend.menu.MenuItem;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@jakarta.persistence.Table(name = "sub_category")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItem> menuItems;
}
