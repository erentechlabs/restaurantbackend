package com.restaurantbackend.restaurantbackend.service.table;// Güncellenmiş TableService (Mapper injection, mapToDTO çağrıları değiştirildi)


import com.restaurantbackend.restaurantbackend.dto.table.CreateTableDTO;
import com.restaurantbackend.restaurantbackend.dto.table.TableDTO;
import com.restaurantbackend.restaurantbackend.dto.table.TableUpdateDTO;
import com.restaurantbackend.restaurantbackend.entity.menu.Restaurant;
import com.restaurantbackend.restaurantbackend.entity.table.Table;
import com.restaurantbackend.restaurantbackend.entity.table.enums.TableStatus;
import com.restaurantbackend.restaurantbackend.mapper.table.TableMapper;
import com.restaurantbackend.restaurantbackend.repository.menu.RestaurantRepository;
import com.restaurantbackend.restaurantbackend.repository.table.TableRepository;
import com.restaurantbackend.restaurantbackend.util.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;

    private final RestaurantRepository restaurantRepository;

    private final TableMapper tableMapper;
    PasswordGenerator passwordGenerator = new PasswordGenerator();

    @Transactional
    public TableDTO createTable(CreateTableDTO dto) {

        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + dto.getRestaurantId()));

        Table table = tableMapper.toEntity(dto);
        table.setNextPassword(passwordGenerator.generateNumericPassword());
        table.setStatus(TableStatus.FREE);
        table.setRestaurant(restaurant);
        table = tableRepository.save(table);
        return tableMapper.toDTO(table);
    }

    public List<TableDTO> getAllTables() {
        return tableMapper.toDTOList(tableRepository.findAll());
    }

    public Optional<TableDTO> getTableById(Long id) {
        return tableRepository.findById(id).map(tableMapper::toDTO);
    }

    public String getNextPassword(Long tableId) {
        Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Table not found with id: " + tableId));
        return table.getNextPassword();
    }

    @Transactional
    public TableDTO updateTable(Long id, TableUpdateDTO dto) {
        Table table = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found"));

        tableMapper.updateEntityFromDTO(table, dto);
        table = tableRepository.save(table);
        return tableMapper.toDTO(table);
    }

    @Transactional
    public void deleteTable(Long id) {
        Table table = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found"));
        tableRepository.deleteById(id);
    }
}