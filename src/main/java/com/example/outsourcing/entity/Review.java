package com.example.outsourcing.entity;

import com.example.outsourcing.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")
@Getter
@NoArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchases_id")
    private Purchases purchases;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "contents")
    private String contents;

    @Column(name = "rating")
    private int rating;

    public Review(Store store, Purchases purchases, User user, String contents, int rating) {
        this.store = store;
        this.purchases = purchases;
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
