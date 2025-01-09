package com.example.outsourcing.dto.review.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CreateReviewRequest {

    private Long purchasesId;
    private Long storeId;
    private String contents;
    private int rating;

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