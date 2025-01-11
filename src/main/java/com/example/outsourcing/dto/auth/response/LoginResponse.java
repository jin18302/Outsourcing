package com.example.outsourcing.dto.auth.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class LoginResponse{

    @JsonProperty("token")
    String token;

    private LoginResponse(String token) {
        this.token = token;
    }

    public static LoginResponse from(String token) {
        return new LoginResponse(token);
    }
}


