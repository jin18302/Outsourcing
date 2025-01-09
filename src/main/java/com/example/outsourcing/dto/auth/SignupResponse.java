package com.example.outsourcing.dto.auth;

import com.example.outsourcing.entity.User;

public class SignupResponse {
    long id;
    String email;
    String address;
    String name;
    UserRole userRole;

    private SignupResponse(long id, String email, String address, String name, UserRole userRole) {
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
                user.getUserRole()
        );
    }
}
