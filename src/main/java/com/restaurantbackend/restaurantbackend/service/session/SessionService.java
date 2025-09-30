package com.restaurantbackend.restaurantbackend.service.session;

import com.restaurantbackend.restaurantbackend.dto.session.StartSessionDTO;
import com.restaurantbackend.restaurantbackend.dto.table.TableSessionDTO;
import com.restaurantbackend.restaurantbackend.entity.session.TableSession;
import com.restaurantbackend.restaurantbackend.entity.table.Table;
import com.restaurantbackend.restaurantbackend.entity.table.enums.TableStatus;
import com.restaurantbackend.restaurantbackend.mapper.table.TableSessionMapper;
import com.restaurantbackend.restaurantbackend.repository.table.TableRepository;
import com.restaurantbackend.restaurantbackend.repository.table.TableSessionRepository;
import com.restaurantbackend.restaurantbackend.util.PasswordGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SessionService {

    private final TableRepository tableRepository;
    private final TableSessionRepository sessionRepository;
    private final TableSessionMapper sessionMapper;
    PasswordGenerator passwordGenerator = new PasswordGenerator();

    public SessionService(TableRepository tableRepository, TableSessionRepository sessionRepository, TableSessionMapper sessionMapper) {
        this.tableRepository = tableRepository;
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
    }

    @Transactional
    public TableSessionDTO startSession(Long tableId, StartSessionDTO dto) {
        Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Masa bulunamadı"));

        if (!table.getNextPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Şifre hatalı");
        }


        endCurrentSession(tableId);

        TableSession session = new TableSession();
        session.setTable(table);
        session.setPassword(dto.getPassword());
        session.setStartTime(LocalDateTime.now());
        session.setActive(true);
        session = sessionRepository.save(session);

        table.setStatus(TableStatus.OCCUPIED);
        tableRepository.save(table);

        return sessionMapper.toDTO(session);
    }

    @Transactional
    public void endSession(Long tableId) {
        endCurrentSession(tableId);

        Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Masa bulunamadı"));

        table.setStatus(TableStatus.FREE);
        table.setNextPassword(passwordGenerator.generateNumericPassword());
        tableRepository.save(table);
    }

    public Optional<TableSessionDTO> getCurrentSession(Long tableId) {
        return sessionRepository.findByTableIdAndActiveTrue(tableId)
                .map(sessionMapper::toDTO);
    }

    private void endCurrentSession(Long tableId) {
        sessionRepository.findByTableIdAndActiveTrue(tableId).ifPresent(session -> {
            session.setActive(false);
            session.setEndTime(LocalDateTime.now());
            sessionRepository.save(session);
        });
    }

}