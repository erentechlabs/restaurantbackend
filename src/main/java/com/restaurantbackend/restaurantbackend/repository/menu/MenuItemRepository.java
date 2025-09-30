package com.restaurantbackend.restaurantbackend.repository.menu;

import com.restaurantbackend.restaurantbackend.entity.menu.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {}