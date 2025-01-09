package com.example.outsourcing.service.auth;

import com.example.outsourcing.entity.User;
import org.springframework.stereotype.Service;

import java.sql.DriverManager;

@Service
public class JdbcUserRepositoryServiceImpl implements UserRepositoryService{
    @Override
    public User findByEmail(String email) {

        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User findByName(String email) {
        return null;
    }
}
