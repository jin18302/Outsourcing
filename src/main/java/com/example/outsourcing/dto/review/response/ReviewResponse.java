package com.example.outsourcing.dto.review.response;

import com.example.outsourcing.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class ReviewResponse {
    private String username;
    private String storename;
    private String menuname;
    private String contents;
    private int rating;

    public static ReviewResponse of(Review review){
        return new ReviewResponse(
                review.getUser().getName(),
                review.getStore().getName(),
                review.getOrder().getMenu().getName(),
                review.getContents(),
                review.getRating()
                );
    }

    public static Page<ReviewResponse> of(Page<Review> review){
        return review.map(ReviewResponse::of);

    }
}
