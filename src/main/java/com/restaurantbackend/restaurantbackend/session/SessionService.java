package com.restaurantbackend.restaurantbackend.session;

import com.restaurantbackend.restaurantbackend.table.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final TableRepository tableRepository;
    private final SessionMapper sessionMapper;

    @Transactional
    public List<SessionDTO> getActiveSessions() {
        return sessionRepository.findAll().stream()
                .filter(Session::isActive)
                .map(sessionMapper::toDTO)
                .collect(Collectors.toList());
    }

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
        session.getOrders().size();

        return sessionMapper.toDTO(session);
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
}
