package com.example.outsourcing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;
    private String address;
    private LocalTime open;
    private LocalTime close;
    private int minAmount;
    private boolean isDeleted = false;

    public Store (String name, String address, LocalTime open, LocalTime close, int minAmount) {
        this.name = name;
        this.address = address;
        this.open = open;
        this.close = close;
        this.minAmount = minAmount;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateAddress(String address) {
        this.address = address;
    }

    public void updateOpen(LocalTime open) {
        this.open = open;
    }

    public void updateClose(LocalTime close) {
        this.close = close;
    }

    public void updateMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public void delete() {
        this.isDeleted = true;
    }
}
