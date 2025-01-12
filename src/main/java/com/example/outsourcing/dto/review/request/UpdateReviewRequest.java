package com.example.outsourcing.dto.review.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateReviewRequest {

    @NotNull
    private final Long reviewId;

    private final String contents;

    @Min(value = 1)
    @Max(value = 5)
    private final Integer rating;

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
