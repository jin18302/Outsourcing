package com.example.outsourcing.dto.user.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDeleteRequest(String password) {

    @JsonCreator
    public UserDeleteRequest(
            @JsonProperty("password") String password
    ) {
        this.password = password;
    }
}
