package com.restaurantbackend.restaurantbackend.session;

import com.restaurantbackend.restaurantbackend.order.OrderDTO;
import com.restaurantbackend.restaurantbackend.order.OrderItemDTO;
import com.restaurantbackend.restaurantbackend.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;
    private final OrderService orderService;

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

    @PostMapping("/start-and-order/{nfctagCode}")
    public ResponseEntity<OrderDTO> startSessionAndOrder(
            @PathVariable String nfctagCode,
            @RequestBody List<OrderItemDTO> items) {
        SessionDTO sessionDTO = sessionService.startSessionByNfc(nfctagCode);
        Session session = sessionService.findByCode(sessionDTO.getSessionCode())
                .orElseThrow(() -> new IllegalArgumentException("Session not found after creation"));
        OrderDTO order = orderService.placeOrder(session, items);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/close/{sessionCode}")
    public ResponseEntity<Void> closeSessionByCode(@PathVariable String sessionCode) {
        sessionService.closeSessionByCode(sessionCode);
        return ResponseEntity.noContent().build();
    }

}
