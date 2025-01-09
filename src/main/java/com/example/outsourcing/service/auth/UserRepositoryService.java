package com.example.outsourcing.service.auth;

import com.example.outsourcing.entity.User;

import java.util.Optional;

public interface UserRepositoryService {

   User findByEmail(String email);

   User save(User user);

   User findByName(String email);
}
