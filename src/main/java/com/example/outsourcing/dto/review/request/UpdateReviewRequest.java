package com.example.outsourcing.dto.review.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UpdateReviewRequest {

    private final Long reviewId;
    private final String contents;
    private final int rating;

    private UpdateReviewRequest(Long reviewId, String contents, int rating) {
        this.reviewId = reviewId;
        this.contents = contents;
        this.rating = rating;
    }

    @JsonCreator
    public static UpdateReviewRequest from(
            @JsonProperty("reviewId")
            Long reviewId,
            @JsonProperty("contents")
            String contents,
            @JsonProperty("rating")
            int rating
    ) {
        return new UpdateReviewRequest(
                reviewId,
                contents,
                rating
        );
    }
}
