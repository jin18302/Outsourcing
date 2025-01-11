package com.example.outsourcing.dto.review.response;

import com.example.outsourcing.entity.Review;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class ReviewResponse {
    private final String userName;
    private final String storeName;
    private final String menuName;
    private final String contents;
    private final int rating;

    private ReviewResponse(String username, String storeName, String menuName, String contents, int rating) {
        this.userName = username;
        this.storeName = storeName;
        this.menuName = menuName;
        this.contents = contents;
        this.rating = rating;
    }

    public static ReviewResponse from(Review review){
        return new ReviewResponse(
                review.getUser().getName(),
                review.getStore().getName(),
                review.getPurchases().getMenu().getName(),
                review.getContents(),
                review.getRating()
                );
    }

    public static Page<ReviewResponse> from(Page<Review> review){
        return review.map(ReviewResponse::from);

    }
}
