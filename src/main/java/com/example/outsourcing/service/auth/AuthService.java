package com.example.outsourcing.service.auth;

import com.example.outsourcing.config.PasswordEncoder;
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
    UserRepositoryService userRepositoryService;
    PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepositoryService.findByEmail(loginRequest.getEmail());
        // 못찾으면 찾을수없는 이메일 익셉션

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("패스워드불일치익셉션");
        }

        return LoginResponse.from(user);

    }

    public SignupResponse signup(SignupRequest signupRequest) {
        User byEmailUser = userRepositoryService.findByEmail(signupRequest.getEmail());
        if (byEmailUser != null) {
            throw new RuntimeException("중복 이메일 익셉션");
        }
        String encode = passwordEncoder.encode(signupRequest.getPassword());

        User user = User.from(signupRequest);
        user.updatePassword(encode);
        User save = userRepositoryService.save(user);
        return SignupResponse.from(save);
    }
}
