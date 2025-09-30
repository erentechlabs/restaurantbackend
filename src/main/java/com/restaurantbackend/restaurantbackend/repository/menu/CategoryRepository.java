package com.restaurantbackend.restaurantbackend.repository.menu;

import com.restaurantbackend.restaurantbackend.entity.menu.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {}
