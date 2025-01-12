package com.example.outsourcing.service.review;

import com.example.outsourcing.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewConnectorInterface {

    Review save(Review review);

    Review findById(Long aLong);

    Page<Review> findReviewByUserId(Long userId, int startRating, int endRating, Pageable page);

    Page<Review> findReviewByStoreId(Long storeId, int startRating, int endRating, Pageable page);

    boolean existsByPurchasesId(Long purchasesId);

    void delete(Review review);

}
