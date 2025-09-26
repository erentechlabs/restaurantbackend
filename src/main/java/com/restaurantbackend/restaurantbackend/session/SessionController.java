package com.restaurantbackend.restaurantbackend.session;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping
    public ResponseEntity<List<SessionDTO>> getActiveSessions() {
        List<SessionDTO> sessions = sessionService.getActiveSessions();
        return ResponseEntity.ok(sessions);
    }

    @PostMapping("/start/{nfctagCode}")
    public ResponseEntity<SessionDTO> startSession(@PathVariable String nfctagCode) {
        SessionDTO session = sessionService.startSessionByNfc(nfctagCode);
        return ResponseEntity.ok(session);
    }

    @PostMapping("/close/{nfctagCode}")
    public ResponseEntity<Void> closeSessionByCode(@PathVariable String nfctagCode) {
        sessionService.closeSessionByCode(nfctagCode);
        return ResponseEntity.noContent().build();
    }

}
