package com.restaurantbackend.restaurantbackend.table;

import org.springframework.stereotype.Component;

@Component
public class TableMapper {

    public TableDTO toDTO(Table table) {
        TableDTO dto = new TableDTO();
        dto.setId(table.getId());
        dto.setNumber(table.getNumber());
        dto.setNfcTagCode(table.getNfcTagCode());
        return dto;
    }

    public Table toEntity(TableDTO dto) {
        Table table = new Table();
        table.setNumber(dto.getNumber());
        table.setNfcTagCode(dto.getNfcTagCode());
        return table;
    }
}
