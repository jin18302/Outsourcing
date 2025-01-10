package com.example.outsourcing.service.review;


import com.example.outsourcing.common.status.PurchasesStatus;
import com.example.outsourcing.dto.review.request.CreateReviewRequest;
import com.example.outsourcing.dto.review.request.UpdateReviewRequest;
import com.example.outsourcing.dto.review.response.ReviewResponse;
import com.example.outsourcing.entity.Purchases;
import com.example.outsourcing.entity.Review;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.entity.User;
import com.example.outsourcing.repository.purchases.PurchasesConnector;
import com.example.outsourcing.repository.review.ReviewConnector;
import com.example.outsourcing.repository.store.StoreConnector;
import com.example.outsourcing.repository.user.UserConnector;
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

    private final ReviewConnector reviewConnector;
    private final PurchasesConnector purchasesConnector;
    private final UserConnector userConnector;
    private final StoreConnector storeConnector;

    @Transactional
    public ReviewResponse saveReview(Long userId, CreateReviewRequest createReviewRequest) {

        User user = userConnector.findById(userId);

        Purchases purchases = purchasesConnector.findById(createReviewRequest.purchasesId());

        if (purchases.getUser().getId() != userId) {
            System.out.println(userId);
            System.out.println(purchases.getUser().getId());
            System.out.println(purchases.getUser().getName());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인된 아이디와 주문자의 아이디가 다릅니다. 해킹이 의심됩니다.");
        }

        if (!purchases.getPurchasesStatus().equals(PurchasesStatus.배달완료)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "주문상태가 배달완료가 아닙니다.");
        }

        Store store = storeConnector.findById(createReviewRequest.storeId());

        Review review = new Review(
                store,
                purchases,
                user,
                createReviewRequest.contents(),
                createReviewRequest.rating());

        return ReviewResponse.from(reviewConnector.save(review));


    }

    public ReviewResponse updateReview(Long userId, UpdateReviewRequest updateReviewRequest) {
        Review review = reviewConnector.findById(updateReviewRequest.reviewId());
        if (review.getUser().getId() != userId) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "리뷰 작성자 본인만 수정할 수 있습니다.");
        }

        if (updateReviewRequest.contents() != null) {
            review.updateContents(updateReviewRequest.contents());
        }
        if (updateReviewRequest.rating() != 0) {
            review.updateRating(updateReviewRequest.rating());
        }
        return ReviewResponse.from(review);
    }

    public Page<ReviewResponse> findReviewByUserId(Long userId, String rating, int pageNumber) {


        Pageable page = PageRequest.of(
                pageNumber + 1,
                10,
                Sort.by(Sort.Order.desc("id")));


        String[] ratingData = rating.split("-");


        return ReviewResponse.from(reviewConnector.findReviewByUserId(
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


        return ReviewResponse.from(reviewConnector.findReviewByStoreId(
                storeId,
                Integer.parseInt(ratingData[0]),
                Integer.parseInt(ratingData[1]),
                page));
    }

    public void removeReview(Long userId, Long reviewId) {
        Review review = reviewConnector.findById(reviewId);
        if (review.getUser().getId() != userId) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "리뷰 작성자 본인만 삭제할 수 있습니다.");
        }
        reviewConnector.delete(review);
    }


}
