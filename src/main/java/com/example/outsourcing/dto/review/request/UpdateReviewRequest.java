package com.example.outsourcing.dto.review.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UpdateReviewRequest {
        private Long reviewId;
        private String contents;
        private int rating;

        @JsonCreator
        public UpdateReviewRequest(
                @JsonProperty("reviewId") Long reviewId,
                @JsonProperty("contents") String contents,
                @JsonProperty("rating") int rating
        ){
            this.reviewId=reviewId;
            this.contents=contents;
            this.rating=rating;
        }

    @JsonCreator
    public UpdateReviewRequest(
            @JsonProperty("reviewId") Long reviewId,
            @JsonProperty("contents") String contents
    ){
        this.reviewId=reviewId;
        this.contents=contents;
        this.rating=0;
    }

}
