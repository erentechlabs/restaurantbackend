package com.restaurantbackend.restaurantbackend.table;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableRepository extends JpaRepository<Table, Long> {
    Optional<Table> findByNfcTagCode(String nfcTagCode);
}