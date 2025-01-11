package com.example.outsourcing.dto.auth.request;

import com.example.outsourcing.common.status.UserRole;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequest{
        @NotBlank
        private final String name;
        @NotBlank
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$",
                message = "비밀번호는 최소 8자 이상이며, 대소문자, 숫자, 특수문자를 각각 최소 1자 이상 포함해야 합니다.")
        private final String password;
        @NotBlank
        private final String address;
        @NotBlank
        @Email
        private final String email;
        @NotBlank
        private final String userRole;

        private SignupRequest(String name, String password, String address, String email, String userRole) {
                this.name = name;
                this.password = password;
                this.address = address;
                this.email = email;
                this.userRole = userRole;
        }
        @JsonCreator
        public static SignupRequest from(
                @JsonProperty("name")
                String name,
                @JsonProperty("password")
                String password,
                @JsonProperty("address")
                String address,
                @JsonProperty("email")
                String email,
                @JsonProperty("userRole")
                String userRole) {
                return new SignupRequest(name,
                        password,
                        address,
                        email,
                        userRole
                );
        }
}
