package com.restaurantbackend.restaurantbackend.repository.table;

import com.restaurantbackend.restaurantbackend.entity.table.Table;
import com.restaurantbackend.restaurantbackend.entity.session.TableSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableSessionRepository extends JpaRepository<TableSession, Long> {
    @Query("SELECT s FROM TableSession s WHERE s.table.id = :tableId AND s.active = true")
    Optional<TableSession> findByTableIdAndActiveTrue(@Param("tableId") Long tableId);

    List<TableSession> findByTable(Table table);
}