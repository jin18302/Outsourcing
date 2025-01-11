package com.example.outsourcing.entity;

import com.example.outsourcing.dto.store.request.StoreRequest;
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
    private Integer minAmount;
    private boolean isDeleted = false;

    private Store (User user, String name, String address, String open, String close, int minAmount) {
        this.user = user;
        this.name = name;
        this.address = address;
        this.open = LocalTime.parse(open);
        this.close = LocalTime.parse(close);
        this.minAmount = minAmount;
    }

    public static Store from(User user,
                             String name,
                             String address,
                             String open,
                             String close,
                             Integer minAmount) {
        return new Store(
                user,
                name,
                address,
                open,
                close,
                minAmount);
    }

    public void updateDetails(
            String name,
            String address,
            String open,
            String close,
            Integer minAmount
    ) {
        if (name != null) {
            this.name = name;
        }

        if (address != null) {
            this.address = address;
        }

        if (open != null) {
            this.open = LocalTime.parse(open);
        }

        if (close != null) {
            this.close = LocalTime.parse(close);
        }

        if (minAmount != null) {
            this.minAmount = minAmount;
        }
    }

    public void delete() {
        this.isDeleted = true;
    }
}
