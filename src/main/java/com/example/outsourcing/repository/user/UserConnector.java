package com.example.outsourcing.repository.user;

import com.example.outsourcing.entity.User;
import com.example.outsourcing.service.auth.UserConnceterInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConnector implements UserConnceterInterface {
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new RuntimeException("유저없음"));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저없음"));
    }

    @Override
    public boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}

