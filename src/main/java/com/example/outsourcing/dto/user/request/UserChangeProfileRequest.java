package com.example.outsourcing.dto.user.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserChangeProfileRequest(
        String name,
        String address) {

    @JsonCreator
    public UserChangeProfileRequest(
            @JsonProperty("name") String name,
            @JsonProperty("address") String address
    ) {
        this.name = name;
        this.address = address;
    }
}
