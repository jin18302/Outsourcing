package com.example.outsourcing.dto.auth.response;

import com.example.outsourcing.common.status.UserRole;
import com.example.outsourcing.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
public class SignupResponse {
    @JsonProperty("id")
    private final long id;
    @JsonProperty("email")
    private final String email;
    @JsonProperty("address")
    private final String address;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("userRole")
    private final String  userRole;

    private SignupResponse(long id, String email, String address, String name, String userRole) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.name = name;
        this.userRole = userRole;
    }

    public static SignupResponse from(User user) {
        return new SignupResponse(
                user.getId(),
                user.getEmail(),
                user.getAddress(),
                user.getName(),
                user.getUserRole().toString()
        );
    }

}
