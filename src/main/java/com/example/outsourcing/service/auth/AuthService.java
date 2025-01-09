package com.example.outsourcing.service.auth;

import com.example.outsourcing.dto.auth.LoginRequest;
import com.example.outsourcing.dto.auth.LoginResponse;
import com.example.outsourcing.dto.auth.SignupRequest;
import com.example.outsourcing.dto.auth.SignupResponse;
import com.example.outsourcing.entity.User;
import com.example.outsourcing.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    UserRepository userRepository;

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        // 못찾으면 찾을수없는 이메일 익셉션

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("패스워드불일치익셉션");
        }

        return LoginResponse.from(user);

    }

    public SignupResponse signup(SignupRequest signupRequest) {
        User byEmailUser = userRepository.findByEmail(signupRequest.getEmail());
        if (byEmailUser != null) {
            throw new RuntimeException("중복 이메일 익셉션");
        }
        User user = User.from(signupRequest);
        User save = userRepository.save(user);
        return SignupResponse.from(save);
    }
}
