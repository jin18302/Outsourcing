package com.example.outsourcing.dto.auth.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;


public record LoginRequest(@NotBlank String email,
                           @NotBlank @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$",
                                   message = "비밀번호는 최소 8자 이상이며, 대소문자, 숫자, 특수문자를 각각 최소 1자 이상 포함해야 합니다.") String password) {

    @JsonCreator
    public LoginRequest(
            @JsonProperty("email") String email,
            @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }
}
