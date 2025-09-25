package com.restaurantbackend.restaurantbackend.session;

import com.restaurantbackend.restaurantbackend.order.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SessionMapper {

    private final OrderMapper orderMapper;

    public SessionDTO toDTO(Session session) {
        SessionDTO dto = new SessionDTO();
        dto.setSessionCode(session.getSessionCode());
        dto.setStartTime(session.getStartTime());
        dto.setEndTime(session.getEndTime());
        dto.setActive(session.isActive());
        dto.setTableId(session.getTable().getId());
        dto.setTableNumber(session.getTable().getNumber());
        dto.setOrders(session.getOrders().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    public Session toEntity(SessionDTO dto, com.restaurantbackend.restaurantbackend.table.Table table) {
        Session session = new Session();
        session.setSessionCode(dto.getSessionCode());
        session.setStartTime(dto.getStartTime());
        session.setEndTime(dto.getEndTime());
        session.setActive(dto.isActive());
        session.setTable(table);

        if (dto.getOrders() != null) {
            session.setOrders(dto.getOrders().stream()
                    .map(orderDTO -> orderMapper.toEntity(session, orderDTO.getItems()))
                    .collect(Collectors.toList()));
        }

        return session;
    }
}
