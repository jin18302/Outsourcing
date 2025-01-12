package com.example.outsourcing.dto.user.response;

import com.example.outsourcing.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponse{
    @JsonProperty("id")
    private final Long id;
    @JsonProperty("email")
    private final String email;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("createdAt")
    private final LocalDateTime createdAt;
    @JsonProperty("modifiedAt")
    private final LocalDateTime modifiedAt;

    private UserResponse(Long id, String email, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static UserResponse from(User user){
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getCreatedAt(),
                user.getModifiedAt());
    }
}
