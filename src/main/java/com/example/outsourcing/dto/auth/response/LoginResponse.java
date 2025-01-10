package com.example.outsourcing.dto.auth.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginResponse {

    private final String token;

}
