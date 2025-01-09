package com.example.outsourcing.entity;

import com.example.outsourcing.dto.auth.SignupRequest;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class User {

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

    public void updateName(String name) {
        this.name = name;
    }

    public void updateAddress(String address) {
        this.address = address;
    }

    public void delete() {
        this.isDeleted = true;
    }

    public User() {
    }

    private User(String email,String address , String name, String password) {
        this.email = email;
        this.address = address;
        this.name =  name;
        this.password = password;
    }

    public static User from(SignupRequest request) {
        return new User(
                request.getEmail(),
                request.getAddress(),
                request.getName(),
                request.getPassword());
    }
}
