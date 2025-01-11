package com.example.outsourcing.dto.review.response;

import com.example.outsourcing.entity.Review;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class ReviewResponse {
    @JsonProperty("userName")
    private final String userName;
    @JsonProperty("storeName")
    private final String storeName;
    @JsonProperty("menuName")
    private final String menuName;
    @JsonProperty("contents")
    private final String contents;
    @JsonProperty("rating")
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
