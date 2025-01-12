package com.example.outsourcing.service;

import com.example.outsourcing.common.status.UserRole;
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
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    AuthService authService;

    @Mock
    UserConnector userConnector;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    void 유저_생성() {
        //given
        SignupRequest signupRequest = SignupRequest.from("한성우", "abc1@adsf23", "서울", "gktjsddn@gmail.com", UserRole.OWNER.toString());

        User user = User.from(signupRequest);

        ReflectionTestUtils.setField(user, "id", 1L);

        //when
        when(passwordEncoder.encode(user.getPassword())).thenReturn("abc1@adsf23");
        when(userConnector.checkEmail(signupRequest.getEmail())).thenReturn(false);
        when(userConnector.save(any(User.class))).thenReturn(user);

        SignupResponse signup = authService.signup(signupRequest);
        //then

        Assertions.assertEquals(signup.getAddress(),user.getAddress());

    }
}
