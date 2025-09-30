package com.restaurantbackend.restaurantbackend.service.table;// Güncellenmiş TableService (Mapper injection, mapToDTO çağrıları değiştirildi)


import com.restaurantbackend.restaurantbackend.dto.table.CreateTableDTO;
import com.restaurantbackend.restaurantbackend.dto.table.TableDTO;
import com.restaurantbackend.restaurantbackend.dto.table.TableUpdateDTO;
import com.restaurantbackend.restaurantbackend.entity.table.Table;
import com.restaurantbackend.restaurantbackend.entity.table.enums.TableStatus;
import com.restaurantbackend.restaurantbackend.mapper.table.TableMapper;
import com.restaurantbackend.restaurantbackend.repository.table.TableRepository;
import com.restaurantbackend.restaurantbackend.util.PasswordGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class TableService {

    private final TableRepository tableRepository;

    private final TableMapper tableMapper;
    PasswordGenerator passwordGenerator = new PasswordGenerator();

    public TableService(TableRepository tableRepository, TableMapper tableMapper) {
        this.tableRepository = tableRepository;
        this.tableMapper = tableMapper;
    }

    @Transactional
    public TableDTO createTable(CreateTableDTO dto) {
        try {
            Table table = tableMapper.toEntity(dto);
            table.setNextPassword(passwordGenerator.generateNumericPassword());
            table.setStatus(TableStatus.FREE);
            table = tableRepository.save(table);
            return tableMapper.toDTO(table);
        } catch (Exception e) {
            throw new RuntimeException("Masa eklenemedi: " + e.getMessage(), e);
        }
    }

    public List<TableDTO> getAllTables() {
        return tableMapper.toDTOList(tableRepository.findAll());
    }

    public Optional<TableDTO> getTableById(Long id) {
        return tableRepository.findById(id).map(tableMapper::toDTO);
    }

    public String getNextPassword(Long tableId) {
        Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Masa bulunamadı"));
        return table.getNextPassword();
    }

    @Transactional
    public TableDTO updateTable(Long id, TableUpdateDTO dto) {
        Table table = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Masa bulunamadı"));

        tableMapper.updateEntityFromDTO(table, dto);
        table = tableRepository.save(table);
        return tableMapper.toDTO(table);
    }

    @Transactional
    public void deleteTable(Long id) {
        Table table = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Masa bulunamadı"));
        tableRepository.deleteById(id);
    }
}