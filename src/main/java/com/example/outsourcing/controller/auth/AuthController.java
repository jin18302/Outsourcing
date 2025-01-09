package com.example.outsourcing.controller.auth;

import com.example.outsourcing.dto.auth.LoginRequest;
import com.example.outsourcing.dto.auth.LoginResponse;
import com.example.outsourcing.dto.auth.SignupRequest;
import com.example.outsourcing.dto.auth.SignupResponse;
import com.example.outsourcing.service.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse login = authService.login(loginRequest);
        return ResponseEntity.ok(login);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(
            @RequestBody SignupRequest signupRequest) {
        SignupResponse signup = authService.signup(signupRequest);
    }
}
