package com.example.outsourcing.dto.user.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserResponse {

    private final Long id;
    private final String email;
    private final String name;

}
