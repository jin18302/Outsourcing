package com.example.outsourcing.dto.user.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserChangePasswordRequest(
        String oldPassword,
        String newPassword) {

    @JsonCreator
    public UserChangePasswordRequest(
            @JsonProperty("oldPassword") String oldPassword,
            @JsonProperty("newPassword") String newPassword
    ) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

}
