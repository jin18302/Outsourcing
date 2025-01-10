package com.example.outsourcing.repository.user;

import com.example.outsourcing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id = :userId AND u.isDeleted = false")
    Optional<User> findByIdAndIsDeletedFalse(@Param("userId") Long userId);

}
