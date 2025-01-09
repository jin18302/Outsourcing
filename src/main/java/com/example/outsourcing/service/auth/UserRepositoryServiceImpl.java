package com.example.outsourcing.service.auth;

import com.example.outsourcing.entity.User;
import com.example.outsourcing.repository.LunaticRepository;
import com.example.outsourcing.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
public class UserRepositoryServiceImpl implements UserRepositoryService{

    private final UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("못찾음 익셉션"));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByName(String email) {
        return userRepository.findByName(email).orElseThrow(()->new RuntimeException("나 못찾아부려"));
    }
}
