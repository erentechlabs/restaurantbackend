package com.restaurantbackend.restaurantbackend.menu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<MenuItem, Long> {}