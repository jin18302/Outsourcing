package com.example.outsourcing.dto.user.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserChangeProfileRequest {

    private final String name;
    private final String address;

}
