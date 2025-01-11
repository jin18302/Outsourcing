package com.example.outsourcing.dto.review.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateReviewRequest(
        @JsonProperty("purchasesId") Long purchasesId,
        @JsonProperty("storeId") Long storeId,
        @JsonProperty("contents") String contents,
        @JsonProperty("rating") int rating) {
}
