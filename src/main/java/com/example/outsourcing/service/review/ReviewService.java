package com.example.outsourcing.service.review;


import com.example.outsourcing.common.exception.InvalidRequestException;
import com.example.outsourcing.common.exception.UnauthorizedException;
import com.example.outsourcing.common.status.PurchasesStatus;
import com.example.outsourcing.dto.review.request.CreateReviewRequest;
import com.example.outsourcing.dto.review.request.UpdateReviewRequest;
import com.example.outsourcing.dto.review.response.ReviewResponse;
import com.example.outsourcing.entity.Purchases;
import com.example.outsourcing.entity.Review;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.entity.User;
import com.example.outsourcing.service.purchases.PurchasesConnectorInterface;
import com.example.outsourcing.service.store.StoreConnectorInterface;
import com.example.outsourcing.service.user.UserConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewConnectorInterface reviewConnectorInterface;
    private final PurchasesConnectorInterface purchasesConnectorInterface;
    private final UserConnectorInterface userConnectorInterface;
    private final StoreConnectorInterface StoreConnectorInterface;

    @Transactional
    public ReviewResponse saveReview(Long userId, CreateReviewRequest createReviewRequest) {

        if (reviewConnectorInterface.existsByPurchasesId(createReviewRequest.getPurchasesId())) {
            throw new InvalidRequestException("이미 리뷰가 달린 주문입니다");
        }

        User user = userConnectorInterface.findById(userId);

        Purchases purchases = purchasesConnectorInterface.findById(createReviewRequest.getPurchasesId());

        if (purchases.getUser().getId() != userId) {
            System.out.println(userId);
            System.out.println(purchases.getUser().getId());
            System.out.println(purchases.getUser().getName());
            throw new UnauthorizedException("로그인된 아이디와 주문자의 아이디가 다릅니다.");
        }

        if (!purchases.getPurchasesStatus().equals(PurchasesStatus.배달완료)) {
            throw new InvalidRequestException("주문상태가 배달완료가 아닙니다.");
        }

        Store store = StoreConnectorInterface.findById(createReviewRequest.getStoreId());

        Review review = new Review(
                store,
                purchases,
                user,
                createReviewRequest.getContents(),
                createReviewRequest.getRating());

        return ReviewResponse.from(reviewConnectorInterface.save(review));


    }

    @Transactional
    public ReviewResponse updateReview(Long userId, UpdateReviewRequest updateReviewRequest) {
        Review review = reviewConnectorInterface.findById(updateReviewRequest.getReviewId());
        if (review.getUser().getId() != userId) {
            throw new UnauthorizedException("리뷰 작성자 본인만 수정할 수 있습니다.");
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

        if (rating.isEmpty()) {
            rating = "1-5";
        }

        Pageable page = PageRequest.of(
                pageNumber - 1,
                10,
                Sort.by(Sort.Order.desc("id")));

        String[] ratingData = rating.split("-");



        return ReviewResponse.from(reviewConnectorInterface.findReviewByUserId(
                userId,
                Integer.parseInt(ratingData[0]),
                Integer.parseInt(ratingData[1]),
                page));
    }

    public Page<ReviewResponse> findReviewByStoreId(Long storeId, String rating, int pageNumber) {

        if (rating.isEmpty()) {
            rating = "1-5";
        }

        Pageable page = PageRequest.of(
                pageNumber - 1,
                10,
                Sort.by(Sort.Order.desc("id")));


        String[] ratingData = rating.split("-");


        return ReviewResponse.from(reviewConnectorInterface.findReviewByStoreId(
                storeId,
                Integer.parseInt(ratingData[0]),
                Integer.parseInt(ratingData[1]),
                page));
    }

    public void removeReview(Long userId, Long reviewId) {
        Review review = reviewConnectorInterface.findById(reviewId);
        if (review.getUser().getId() != userId) {
            throw new UnauthorizedException("리뷰 작성자 본인만 삭제할 수 있습니다.");
        }
        reviewConnectorInterface.delete(review);
    }


}
