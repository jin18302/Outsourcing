package com.example.outsourcing.dto.review.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateReviewRequest(
        Long purchasesId,
        Long storeId,
        String contents,
        int rating) {
    @JsonCreator
    public CreateReviewRequest(
            @JsonProperty("purchasesId") Long purchasesId,
            @JsonProperty("storeId") Long storeId,
            @JsonProperty("contents") String contents,
            @JsonProperty("rating") int rating){
        this.purchasesId=purchasesId;
        this.storeId=storeId;
        this.contents=contents;
        this.rating=rating;
    }
}
