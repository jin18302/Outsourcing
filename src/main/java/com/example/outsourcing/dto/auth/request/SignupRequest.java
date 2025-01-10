package com.example.outsourcing.dto.auth.request;

import com.example.outsourcing.common.status.UserRole;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;


public record SignupRequest(@NotBlank String name,
                            @NotBlank @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$",
                                    message = "비밀번호는 최소 8자 이상이며, 대소문자, 숫자, 특수문자를 각각 최소 1자 이상 포함해야 합니다.") String password,
                            @NotBlank String address, @NotBlank @Email String email, @NotBlank String userRole) {

    @JsonCreator
    public SignupRequest(
            @JsonProperty("name") String name,
            @JsonProperty("password") String password,
            @JsonProperty("address") String address,
            @JsonProperty("email") String email,
            @JsonProperty("userRole")String userRole) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.email = email;
        this.userRole = userRole;
    }
}
