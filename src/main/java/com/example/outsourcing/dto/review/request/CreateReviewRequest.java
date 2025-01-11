package com.example.outsourcing.dto.review.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateReviewRequest {
    @NotBlank
    private final Long purchasesId;
    @NotBlank
    private final Long storeId;
    @NotBlank
    private final String contents;
    @NotBlank
    @Min(value = 1)
    @Max(value = 5)
    private final int rating;

    private CreateReviewRequest(Long purchasesId, Long storeId, String contents, int rating) {
        this.purchasesId = purchasesId;
        this.storeId = storeId;
        this.contents = contents;
        this.rating = rating;
    }

    @JsonCreator
    public static CreateReviewRequest from(
            @JsonProperty("purchasesId")
            Long purchasesId,
            @JsonProperty("storeId")
            Long storeId,
            @JsonProperty("contents")
            String contents,
            @JsonProperty("rating")
            int rating
    ) {
        return new CreateReviewRequest(
                purchasesId,
                storeId,
                contents,
                rating
        );
    }
}
