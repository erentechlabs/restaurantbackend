package com.restaurantbackend.restaurantbackend.table;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;

    public List<TableDTO> getAll() {
        return tableRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TableDTO add(TableDTO dto) {
        Table table = new Table();
        table.setNumber(dto.getNumber());
        table.setNfcTagCode(dto.getNfcTagCode());
        Table saved = tableRepository.save(table);
        return convertToDTO(saved);
    }

    public TableDTO update(Long id, TableDTO dto) {
        Table existing = tableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Table not found: " + id));
        existing.setNumber(dto.getNumber());
        existing.setNfcTagCode(dto.getNfcTagCode());
        Table saved = tableRepository.save(existing);
        return convertToDTO(saved);
    }

    public void delete(Long id) {
        tableRepository.deleteById(id);
    }

    private TableDTO convertToDTO(Table table) {
        TableDTO dto = new TableDTO();
        dto.setId(table.getId());
        dto.setNumber(table.getNumber());
        dto.setNfcTagCode(table.getNfcTagCode());
        return dto;
    }
}
