package com.example.outsourcing.controller.auth;

import com.example.outsourcing.dto.auth.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthController {

    @PostMapping
    public ResponseEntity<> login(@RequestBody LoginRequest loginRequest) {

    }
}
