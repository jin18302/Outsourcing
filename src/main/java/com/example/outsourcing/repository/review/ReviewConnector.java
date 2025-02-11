package com.example.outsourcing.repository.review;

import com.example.outsourcing.entity.Review;
import com.example.outsourcing.service.review.ReviewConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;


@Component
@RequiredArgsConstructor
public class ReviewConnector implements ReviewConnectorInterface {
    private final ReviewRepository reviewRepository;

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review findById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "리뷰가 없습니다."));
    }

    @Override
    public Page<Review> findReviewByUserId(Long userId, int startRating, int endRating, Pageable page) {
        return reviewRepository.findReviewByUserId(
                userId,
                startRating,
                endRating,
                page);
    }

    @Override
    public Page<Review> findReviewByStoreId(Long storeId, int startRating, int endRating, Pageable page) {
    return reviewRepository.findReviewByStoreId(
            storeId,
            startRating,
            endRating,
            page
    );
    }

    @Override
    public boolean existsByPurchasesId(Long purchasesId) {
        return reviewRepository.existsByPurchasesId(purchasesId);
    }

    @Override
    public void delete(Review review) {
        reviewRepository.delete(review);
    }
}
