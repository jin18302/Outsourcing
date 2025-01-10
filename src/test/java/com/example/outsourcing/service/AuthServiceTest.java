package com.example.outsourcing.service;

import com.example.outsourcing.common.status.UserRole;
import com.example.outsourcing.dto.auth.request.SignupRequest;
import com.example.outsourcing.entity.User;
import org.junit.jupiter.api.Test;

public class AuthServiceTest {

    @Test
    void 유저_생성() {
        //given
        User user = User.from(new SignupRequest("한성우", "abc123", "서울", "gkstjddn@gmail.com", UserRole.OWNER));
        //when

        //then
    }
}
