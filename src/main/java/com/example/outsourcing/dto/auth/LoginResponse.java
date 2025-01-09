package com.example.outsourcing.dto.auth;

import com.example.outsourcing.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private Long id;
    private String email;
    private String address;

    public static LoginResponse from(User user) {
        return new LoginResponse(
                user.getId(),
                user.getEmail(),
                user.getAddress()
        );
    }
}
