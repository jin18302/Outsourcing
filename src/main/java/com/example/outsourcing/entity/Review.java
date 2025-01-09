package com.example.outsourcing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "contents")
    private String contents;

    @Column(name = "rating")
    private int rating;

    public Review(Store store, Order order, User user, String contents, int rating) {
        this.store = store;
        this.order = order;
        this.user = user;
        this.contents = contents;
        this.rating=rating;
    }


    public void updateContents(String contents) {
        this.contents = contents;
    }

    public void updateRating(int rating) {
        this.rating = rating;
    }

}
