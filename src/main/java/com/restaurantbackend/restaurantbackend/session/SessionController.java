package com.restaurantbackend.restaurantbackend.session;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions/")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping("active")
    public List<SessionDTO> getActiveSessions() {
        return sessionService.getActiveSessions();
    }

    @PostMapping("start/{nfctagCode}")
    public SessionDTO startSession(@PathVariable String nfctagCode) {
        return sessionService.startSessionByNfc(nfctagCode);
    }

    @PostMapping("close/{nfctagCode}")
    public void closeSessionByCode(@PathVariable String nfctagCode) {
        sessionService.closeSessionByCode(nfctagCode);
    }

}
