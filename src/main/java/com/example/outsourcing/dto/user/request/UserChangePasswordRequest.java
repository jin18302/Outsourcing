package com.example.outsourcing.dto.user.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserChangePasswordRequest {

    private final String oldPassword;
    private final String newPassword;
}
