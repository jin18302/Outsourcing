package com.example.outsourcing.dto.review.response;

import com.example.outsourcing.entity.Review;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

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
    @JsonProperty("createdAt")
    private final LocalDateTime createdAt;
    @JsonProperty("modifiedAt")
    private final LocalDateTime modifiedAt;

    private ReviewResponse(String username, String storeName, String menuName, String contents, int rating, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.userName = username;
        this.storeName = storeName;
        this.menuName = menuName;
        this.contents = contents;
        this.rating = rating;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static ReviewResponse from(Review review){
        return new ReviewResponse(
                review.getUser().getName(),
                review.getStore().getName(),
                review.getPurchases().getMenu().getName(),
                review.getContents(),
                review.getRating(),
                review.getCreatedAt(),
                review.getModifiedAt()
                );
    }

    public static Page<ReviewResponse> from(Page<Review> review){
        return review.map(ReviewResponse::from);

    }
}
