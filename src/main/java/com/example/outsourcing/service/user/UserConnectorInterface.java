package com.example.outsourcing.service.user;

import com.example.outsourcing.entity.User;

public interface UserConnectorInterface {

        User save(User user);

        User findById(Long userId);

        boolean checkEmail(String email);

        User findByEmail(String email);
}
