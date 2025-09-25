package com.restaurantbackend.restaurantbackend.table;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;
    private final TableMapper tableMapper;

    public List<TableDTO> getAll() {
        return tableRepository.findAll()
                .stream()
                .map(tableMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TableDTO add(TableDTO dto) {
        Table table = tableMapper.toEntity(dto);
        Table saved = tableRepository.save(table);
        return tableMapper.toDTO(saved);
    }

    public TableDTO update(Long id, TableDTO dto) {
        Table existing = tableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Table not found: " + id));

        existing.setNumber(dto.getNumber());
        existing.setNfcTagCode(dto.getNfcTagCode());

        Table saved = tableRepository.save(existing);
        return tableMapper.toDTO(saved);
    }

    public void delete(Long id) {
        tableRepository.deleteById(id);
    }
}
