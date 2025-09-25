package com.restaurantbackend.restaurantbackend.table;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tables")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @GetMapping("/all")
    public List<TableDTO> getAllTables() {
        return tableService.getAll();
    }

    @PostMapping("/add")
    public TableDTO addTable(@RequestBody TableDTO dto) {
        return tableService.add(dto);
    }

    @PutMapping("/update/{id}")
    public TableDTO updateTable(@PathVariable Long id, @RequestBody TableDTO dto) {
        return tableService.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTable(@PathVariable Long id) {
        tableService.delete(id);
    }
}
