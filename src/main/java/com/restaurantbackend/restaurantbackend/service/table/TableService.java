package com.restaurantbackend.restaurantbackend.service.table;// Güncellenmiş TableService (Mapper injection, mapToDTO çağrıları değiştirildi)


import com.restaurantbackend.restaurantbackend.dto.table.CreateTableDTO;
import com.restaurantbackend.restaurantbackend.dto.table.TableDTO;
import com.restaurantbackend.restaurantbackend.dto.table.TableUpdateDTO;
import com.restaurantbackend.restaurantbackend.entity.table.Table;
import com.restaurantbackend.restaurantbackend.entity.table.enums.TableStatus;
import com.restaurantbackend.restaurantbackend.mapper.table.TableMapper;
import com.restaurantbackend.restaurantbackend.repository.table.TableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class TableService {

    private final TableRepository tableRepository;

    private final TableMapper tableMapper;

    public TableService(TableRepository tableRepository, TableMapper tableMapper) {
        this.tableRepository = tableRepository;
        this.tableMapper = tableMapper;
    }

    @Transactional
    public TableDTO createTable(CreateTableDTO dto) {
        try {
            Table table = tableMapper.toEntity(dto);
            table.setNextPassword(generatePassword());
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
        Optional<Table> optTable = tableRepository.findById(tableId);
        if (optTable.isEmpty()) {
            throw new RuntimeException("Masa bulunamadı");
        }
        return optTable.get().getNextPassword();
    }

    @Transactional
    public TableDTO updateTable(Long id, TableUpdateDTO dto) {
        Optional<Table> optTable = tableRepository.findById(id);
        if (optTable.isEmpty()) {
            throw new RuntimeException("Masa bulunamadı");
        }
        Table table = optTable.get();
        tableMapper.updateEntityFromDTO(table, dto);
        table = tableRepository.save(table);
        return tableMapper.toDTO(table);
    }

    @Transactional
    public void deleteTable(Long id) {
        Optional<Table> optTable = tableRepository.findById(id);
        if (optTable.isEmpty()) {
            throw new RuntimeException("Masa bulunamadı");
        }
        tableRepository.deleteById(id);
    }

    private String generatePassword() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}