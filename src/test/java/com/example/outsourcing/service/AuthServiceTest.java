package com.example.outsourcing.service;

import com.example.outsourcing.common.status.UserRole;
import com.example.outsourcing.config.JwtFilter;
import com.example.outsourcing.config.JwtUtil;
import com.example.outsourcing.config.PasswordEncoder;
import com.example.outsourcing.dto.auth.request.SignupRequest;
import com.example.outsourcing.dto.auth.response.SignupResponse;
import com.example.outsourcing.entity.User;
import com.example.outsourcing.repository.user.UserConnector;
import com.example.outsourcing.service.auth.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    AuthService authService;

    @Mock
    UserConnector userConnector;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    User hi;

    @Test
    void 유저_생성() {
        //given
        User user = User.from(new SignupRequest("한성우", "abc1@adsf23", "서울", "gkstjddn@gmail.com", "owner"), UserRole.OWNER);
        SignupRequest signupRequest = new SignupRequest("한성우", "abc123", "서울", "gktjsddn@gmail.com", "owner");

        //when
        when(hi.getId()).thenReturn(1L);
        when(hi.getAddress()).thenReturn("서울");
        when(userConnector.findByEmail(signupRequest.email())).thenReturn(null);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("sasdf");
        when(userConnector.save(any(User.class))).thenReturn(hi);

        SignupResponse signup = authService.signup(signupRequest);
        //then

        Assertions.assertEquals(signup.getAddress(),user.getAddress());

    }
}
