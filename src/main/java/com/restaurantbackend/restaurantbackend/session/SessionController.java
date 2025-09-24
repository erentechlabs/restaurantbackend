package com.restaurantbackend.restaurantbackend.session;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping("/start/nfc/{tagCode}")
    public SessionDTO startSession(@PathVariable String tagCode) {
        return sessionService.startSessionByNfc(tagCode);
    }

    @PostMapping("/close/code/{code}")
    public void closeSessionByCode(@PathVariable String code) {
        sessionService.closeSessionByCode(code);
    }

}
