package com.restaurantbackend.restaurantbackend.session;

import com.restaurantbackend.restaurantbackend.order.Order;
import com.restaurantbackend.restaurantbackend.order.OrderDTO;
import com.restaurantbackend.restaurantbackend.order.OrderItemDTO;
import com.restaurantbackend.restaurantbackend.table.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final TableRepository tableRepository;

    @Transactional
    public SessionDTO startSessionByNfc(String nfcTagCode) {
        var table = tableRepository.findByNfcTagCode(nfcTagCode)
                .orElseThrow(() -> new IllegalArgumentException("NFC tag not mapped to any table"));

        Optional<Session> activeSession = table.getSessions().stream()
                .filter(Session::isActive)
                .findFirst();

        Session session = activeSession.orElseGet(() -> {
            Session s = new Session();
            s.setTable(table);
            s.setStartTime(LocalDateTime.now());
            s.setSessionCode(UUID.randomUUID().toString());
            s.setActive(true);
            return sessionRepository.save(s);
        });

        return convertToDTO(session);
    }

    @Transactional
    public void closeSessionByCode(String code) {
        sessionRepository.findBySessionCode(code).ifPresent(session -> {
            session.setActive(false);
            session.setEndTime(LocalDateTime.now());
            sessionRepository.save(session);
        });
    }


    public Optional<Session> findByCode(String code) {
        return sessionRepository.findBySessionCode(code);
    }

    private SessionDTO convertToDTO(Session session) {
        SessionDTO dto = new SessionDTO();
        dto.setSessionCode(session.getSessionCode());
        dto.setStartTime(session.getStartTime());
        dto.setActive(session.isActive());
        dto.setTableId(session.getTable().getId());
        dto.setTableNumber(session.getTable().getNumber());
        dto.setOrders(session.getOrders().stream()
                .map(this::convertOrderToDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    private OrderDTO convertOrderToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderTime(order.getOrderTime());
        dto.setItems(order.getItems().stream().map(item -> {
            OrderItemDTO itemDTO = new OrderItemDTO();
            itemDTO.setMenuItemName(item.getMenuItem().getName());
            itemDTO.setQuantity(item.getQuantity());
            return itemDTO;
        }).collect(Collectors.toList()));
        return dto;
    }
}
