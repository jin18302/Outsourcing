package com.example.outsourcing.dto.user.response;

import com.example.outsourcing.entity.User;
import lombok.Getter;

@Getter
public class UserResponse{
    private final Long id;
    private final String email;
    private final String name;

    private UserResponse(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public static UserResponse from(User user){
        return new UserResponse(user.getId(), user.getEmail(), user.getName());
    }
}
