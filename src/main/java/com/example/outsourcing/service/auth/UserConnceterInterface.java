package com.example.outsourcing.service.auth;

import com.example.outsourcing.entity.User;

public interface UserConnceterInterface {
        User save(User user);


        User findById(Long userId);

        boolean checkEmail(String email);

        User findByEmail(String email);

}
