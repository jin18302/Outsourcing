package com.example.outsourcing.dto.user.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserDeleteRequest {

    private final String password;
}
