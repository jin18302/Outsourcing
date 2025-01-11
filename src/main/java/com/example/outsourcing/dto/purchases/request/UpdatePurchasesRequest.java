package com.example.outsourcing.dto.purchases.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class UpdatePurchasesRequest{

    @NotBlank
    Long purchasesId;

    @NotBlank
    String purchasesStatus;

    private UpdatePurchasesRequest(Long purchasesId, String purchasesStatus) {
        this.purchasesId = purchasesId;
        this.purchasesStatus = purchasesStatus;
    }

    public static UpdatePurchasesRequest from(
            @JsonProperty("purchasesId")
            Long purchasesId,
            @JsonProperty("purchasesStatus")
            String purchasesStatus
    ) {
        return new UpdatePurchasesRequest(
                purchasesId,
                purchasesStatus
        );
    }
}
