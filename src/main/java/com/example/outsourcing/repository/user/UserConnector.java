package com.example.outsourcing.repository.user;

import com.example.outsourcing.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConnector {
    private final UserRepository userRepository;


    public User save(User user) {
        return null;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("유저없음"));
    }
}

