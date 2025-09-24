package com.restaurantbackend.restaurantbackend.session;
import com.restaurantbackend.restaurantbackend.table.Table;
import com.restaurantbackend.restaurantbackend.table.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

// ... existing code ...
@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final TableRepository tableRepository;

    public Session startSessionByNfc(String nfcTagCode) {
        Table managedTable = tableRepository.findByNfcTagCode(nfcTagCode)
                .orElseThrow(() -> new IllegalArgumentException("NFC tag not mapped to any table: " + nfcTagCode));

        // İsteğe bağlı: zaten aktif bir session varsa onu döndür (yenisini yaratma)
        Optional<Session> existingActive = sessionRepository.findAll().stream()
                .filter(s -> s.isActive() && s.getTable() != null && s.getTable().getId().equals(managedTable.getId()))
                .findFirst();
        if (existingActive.isPresent()) {
            return existingActive.get();
        }

        Session session = new Session();
        session.setTable(managedTable);
        session.setStartTime(LocalDateTime.now());
        session.setSessionCode(UUID.randomUUID().toString());
        session.setActive(true);
        return sessionRepository.save(session);
    }

    public Optional<Session> findByCode(String code) {
        return sessionRepository.findBySessionCode(code);
    }

    public void closeSession(Long sessionId) {
        sessionRepository.findById(sessionId).ifPresent(session -> {
            session.setActive(false);
            session.setEndTime(LocalDateTime.now());
            sessionRepository.save(session);
        });
    }
}