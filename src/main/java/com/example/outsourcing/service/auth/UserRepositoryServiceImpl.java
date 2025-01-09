package com.example.outsourcing.service.auth;

import com.example.outsourcing.entity.User;
import com.example.outsourcing.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRepositoryServiceImpl implements UserRepositoryService{
    UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("못찾음 익셉션"));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
