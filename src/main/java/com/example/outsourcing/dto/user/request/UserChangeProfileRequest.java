package com.example.outsourcing.dto.user.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserChangeProfileRequest{

    private final String name;
    private final String address;

    private UserChangeProfileRequest(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @JsonCreator
    public static UserChangeProfileRequest from(
            @JsonProperty("name")
            String name,
            @JsonProperty("address")
            String address
    ) {
        return new UserChangeProfileRequest(
                name,
                address
        );
    }
}
