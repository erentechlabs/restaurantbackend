package com.restaurantbackend.restaurantbackend.controller.table;

import com.restaurantbackend.restaurantbackend.dto.table.CreateTableDTO;
import com.restaurantbackend.restaurantbackend.dto.table.TableDTO;
import com.restaurantbackend.restaurantbackend.dto.table.TableUpdateDTO;
import com.restaurantbackend.restaurantbackend.service.table.TableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tables")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping
    public ResponseEntity<TableDTO> createTable(@RequestBody CreateTableDTO dto) {
        try {
            TableDTO table = tableService.createTable(dto);
            return ResponseEntity.ok(table);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TableDTO>> getAllTables() {
        return ResponseEntity.ok(tableService.getAllTables());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableDTO> getTableById(@PathVariable Long id) {
        return tableService.getTableById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/next-password")
    public ResponseEntity<String> getNextPassword(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tableService.getNextPassword(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TableDTO> updateTable(@PathVariable Long id, @RequestBody TableUpdateDTO dto) {
        try {
            TableDTO updated = tableService.updateTable(id, dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        try {
            tableService.deleteTable(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}