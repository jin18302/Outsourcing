package com.example.outsourcing.entity;

import com.example.outsourcing.common.entity.BaseEntity;
import com.example.outsourcing.common.status.UserRole;
import com.example.outsourcing.dto.auth.request.SignupRequest;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;


    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateProfile(String name, String address) {

        if (name != null) {
            this.name = name;
        }

        if (address != null) {
            this.address = address;
        }
    }

    public void delete() {
        this.isDeleted = true;
        this.name = "탈퇴한 유저";
    }

    public User() {
    }

    private User(String email,String address , String name, String password, UserRole userRole) {
        this.email = email;
        this.address = address;
        this.name =  name;
        this.password = password;
        this.userRole = userRole;
    }

    public static User from(SignupRequest signupRequest) {
        return new User(
                signupRequest.getEmail(),
                signupRequest.getAddress(),
                signupRequest.getName(),
                signupRequest.getPassword(),
                UserRole.of(signupRequest.getUserRole())
                );
    }
}
