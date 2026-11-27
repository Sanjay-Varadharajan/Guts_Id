package com.guts_id.gutsId.controller;


import com.guts_id.gutsId.dto.IamLoginRequest;
import com.guts_id.gutsId.model.GutsSession;
import com.guts_id.gutsId.service.GutsAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth/gutsId")
public class GutsAuthController {

    private final GutsAuthenticationService gutsAuthenticationService;

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody IamLoginRequest request) {

        GutsSession session = gutsAuthenticationService.authenticate(request);

        ResponseCookie cookie = ResponseCookie
                .from("GUTSID_SESSION", session.getSessionId())
                .httpOnly(true)
                .secure(false)
                .path("/")
                .sameSite("Lax")
                .maxAge(Duration.between(
                        Instant.now(), session.getExpiresAt()))
                .build();

        return ResponseEntity.ok()
                .header(    HttpHeaders.SET_COOKIE, cookie.toString())
                .body("GutsID authentication successful");
    }


}
