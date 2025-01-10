package com.example.outsourcing.service.auth;

import com.example.outsourcing.common.exception.InvalidRequestException;
import com.example.outsourcing.common.status.UserRole;
import com.example.outsourcing.config.JwtUtil;
import com.example.outsourcing.config.PasswordEncoder;
import com.example.outsourcing.dto.auth.request.LoginRequest;
import com.example.outsourcing.dto.auth.request.SignupRequest;
import com.example.outsourcing.dto.auth.response.LoginResponse;
import com.example.outsourcing.dto.auth.response.SignupResponse;
import com.example.outsourcing.entity.User;
import com.example.outsourcing.repository.user.UserConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserConnector userConnector;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userConnector.findByEmail(loginRequest.getEmail());

        if (user.isDeleted()) {
            throw new InvalidRequestException("삭제된 아이디입니다.");
        }

        // 못찾으면 찾을수없는 이메일 익셉션
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidRequestException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtUtil.createToken(user.getId(), user.getEmail(), user.getUserRole());

        return new LoginResponse(token);
    }

    public SignupResponse signup(SignupRequest signupRequest) {
        boolean byEmailUser = userConnector.checkEmail(signupRequest.getEmail());
        if (byEmailUser) {
            throw new InvalidRequestException("중복된 이메일이 존재합니다.");
        }
        String encode = passwordEncoder.encode(signupRequest.getPassword());

        UserRole userRole = UserRole.of(signupRequest.getUserRole());
        User user = User.from(signupRequest, userRole);
        user.updatePassword(encode);
        User save = userConnector.save(user);
        return SignupResponse.from(save);
    }
}
