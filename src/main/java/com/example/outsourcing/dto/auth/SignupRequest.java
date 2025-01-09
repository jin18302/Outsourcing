package com.example.outsourcing.dto.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class SignupRequest {

    private final String email;

    private final String name;

    private final String password;

    private final String address;

    private final UserRole userRole;

    @JsonCreator
    public SignupRequest(
            @JsonProperty("name") String name,
            @JsonProperty("password") String password,
            @JsonProperty("address") String address,
            @JsonProperty("email") String email) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.email = email;
    }
}
