package com.example.outsourcing.service.auth;

import com.example.outsourcing.config.JwtUtil;
import com.example.outsourcing.config.PasswordEncoder;
import com.example.outsourcing.dto.auth.LoginRequest;
import com.example.outsourcing.dto.auth.LoginResponse;
import com.example.outsourcing.dto.auth.SignupRequest;
import com.example.outsourcing.dto.auth.SignupResponse;
import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.entity.User;
import com.example.outsourcing.repository.LunaticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final LunaticRepository<User> lunaticRepository;
    private final LunaticRepository<Menu> menuLunaticRepository;

    private final UserRepositoryService userRepositoryService;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest loginRequest) {
        User user = findByEmail(loginRequest.getEmail());

        if (user.isDeleted()) {
            throw new RuntimeException("삭제된 유저 익셉션");
        }

        // 못찾으면 찾을수없는 이메일 익셉션
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("패스워드불일치익셉션");
        }

        String token = jwtUtil.createToken(user.getId(), user.getEmail(), user.getUserRole());

        return new LoginResponse(token);
    }

    public SignupResponse signup(SignupRequest signupRequest) {
        User byEmailUser = findByEmail(signupRequest.getEmail());
        if (byEmailUser != null) {
            throw new RuntimeException("중복 이메일 익셉션");
        }
        String encode = passwordEncoder.encode(signupRequest.getPassword());

        User user = User.from(signupRequest);
        user.updatePassword(encode);
        User save = lunaticRepository.save(user);
        return SignupResponse.from(save);
    }

    private User findByEmail(String loginRequest) {
        return lunaticRepository.findByEmail(loginRequest).orElseThrow(() -> new RuntimeException("유저어디감?"));
    }
}
