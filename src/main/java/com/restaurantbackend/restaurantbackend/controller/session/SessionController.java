package com.restaurantbackend.restaurantbackend.controller.session;

import com.restaurantbackend.restaurantbackend.dto.session.StartSessionDTO;
import com.restaurantbackend.restaurantbackend.dto.table.TableSessionDTO;
import com.restaurantbackend.restaurantbackend.service.session.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/tables/{tableId}/start")
    public ResponseEntity<?> startSession(@PathVariable Long tableId, @RequestBody StartSessionDTO dto) {
        try {
            TableSessionDTO session = sessionService.startSession(tableId, dto);
            return ResponseEntity.ok(session);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/tables/{tableId}/end")
    public ResponseEntity<Void> endSession(@PathVariable Long tableId) {
        sessionService.endSession(tableId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tables/{tableId}")
    public ResponseEntity<TableSessionDTO> getCurrentSession(@PathVariable Long tableId) {
        return sessionService.getCurrentSession(tableId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}