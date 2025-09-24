package com.restaurantbackend.restaurantbackend.session;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// ... existing code ...
@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;

    @PostMapping("/start/nfc/{tagCode}")
    public Session startSessionByNfc(@PathVariable String tagCode) {
        return sessionService.startSessionByNfc(tagCode);
    }

    @PostMapping("/close/{id}")
    public void closeSession(@PathVariable Long id) {
        sessionService.closeSession(id);
    }
}