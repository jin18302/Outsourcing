package com.example.outsourcing.dto.user.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserDeleteRequest{

    @NotBlank
    private final String password;

    private UserDeleteRequest(String password) {
        this.password = password;
    }

    @JsonCreator
    public static UserDeleteRequest from(
            @JsonProperty("password")
            String password
    ) {
        return new UserDeleteRequest(password);
    }
}
