package com.restaurantbackend.restaurantbackend.table;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;

    public List<Table> getAll() {
        return tableRepository.findAll();
    }

    public Table add(Table table) {
        // Sadece number alanını kullanmak istiyorsanız, güvenli şekilde set edebilirsiniz.
        // Table newTable = new Table();
        // newTable.setNumber(table.getNumber());
        // return tableRepository.save(newTable);
        return tableRepository.save(table);
    }

    public Table update(Long id, Table data) {
        Table existing = tableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Table not found: " + id));
        existing.setNumber(data.getNumber());
        return tableRepository.save(existing);
    }

    public void delete(Long id) {
        tableRepository.deleteById(id);
    }
}