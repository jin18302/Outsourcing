package com.example.outsourcing.service.review;


import com.example.outsourcing.common.status.PurchasesStatus;
import com.example.outsourcing.dto.review.request.CreateReviewRequest;
import com.example.outsourcing.dto.review.request.UpdateReviewRequest;
import com.example.outsourcing.dto.review.response.ReviewResponse;
import com.example.outsourcing.entity.*;
import com.example.outsourcing.repository.purchases.PurchasesRepository;
import com.example.outsourcing.repository.review.ReviewRepository;
import com.example.outsourcing.service.store.StoreService;
import com.example.outsourcing.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PurchasesRepository purchasesRepository;
    private final UserService userService;
    private final StoreService storeService;

    @Transactional
    public ReviewResponse saveReview(Long userId, CreateReviewRequest createReviewRequest) {

        User user = userService.getUserById(userId);

        Purchases purchases = purchasesRepository.findById(createReviewRequest.getPurchasesId()).
                orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND), "주문정보를 찾을 수 없습니다."));

        if (purchases.getUser().getId() != userId ) {
            System.out.println(userId);
            System.out.println(purchases.getUser().getId());
            System.out.println(purchases.getUser().getName());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인된 아이디와 주문자의 아이디가 다릅니다. 해킹이 의심됩니다.");
        }

        if (purchases.getOrderStatus().equals(PurchasesStatus.배달완료)) {
            Store store = storeService.getStoreById(createReviewRequest.getStoreId());

            Review review = new Review(
                    store,
                    purchases,
                    user,
                    createReviewRequest.getContents(),
                    createReviewRequest.getRating());

            return ReviewResponse.from(reviewRepository.save(review));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "주문상태가 배달완료가 아닙니다.");
        }


    }

    public ReviewResponse updateReview(Long userId, UpdateReviewRequest updateReviewRequest) {
        Review review = reviewRepository.findById(updateReviewRequest.getReviewId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "수정할 리뷰가 없습니다."));
        if (review.getUser().getId() != userId) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "리뷰 작성자 본인만 수정할 수 있습니다.");
        }

        if (updateReviewRequest.getContents() != null) {
            review.updateContents(updateReviewRequest.getContents());
        }
        if (updateReviewRequest.getRating() != 0) {
            review.updateRating(updateReviewRequest.getRating());
        }
        return ReviewResponse.from(review);
    }

    public Page<ReviewResponse> findReviewByUserId(Long userId, String rating, int pageNumber) {


        Pageable page = PageRequest.of(
                pageNumber + 1,
                10,
                Sort.by(Sort.Order.desc("id")));


        String[] ratingData = rating.split("-");


        return ReviewResponse.from(reviewRepository.findReviewByUserId(
                userId,
                Integer.parseInt(ratingData[0]),
                Integer.parseInt(ratingData[1]),
                page));

    }

    public Page<ReviewResponse> findReviewByStoreId(Long storeId, String rating, int pageNumber) {

        Pageable page = PageRequest.of(
                pageNumber + 1,
                10,
                Sort.by(Sort.Order.desc("id")));


        String[] ratingData = rating.split("-");


        return ReviewResponse.from(reviewRepository.findReviewByStoreId(
                storeId,
                Integer.parseInt(ratingData[0]),
                Integer.parseInt(ratingData[1]),
                page));
    }

    public void removeReview(Long userId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제할 리뷰가 없습니다."));
        if (review.getUser().getId() != userId) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "리뷰 작성자 본인만 삭제할 수 있습니다.");
        }
        reviewRepository.delete(review);
    }


}
