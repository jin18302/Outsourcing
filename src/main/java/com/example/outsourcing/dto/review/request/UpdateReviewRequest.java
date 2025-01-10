package com.example.outsourcing.dto.review.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateReviewRequest(
        Long reviewId,
        String contents,
        int rating) {

    @JsonCreator
    public UpdateReviewRequest(
            @JsonProperty("reviewId") Long reviewId,
            @JsonProperty("contents") String contents,
            @JsonProperty("rating") int rating
    ) {
        this.reviewId = reviewId;
        this.contents = contents;
        this.rating = rating;
    }

}
