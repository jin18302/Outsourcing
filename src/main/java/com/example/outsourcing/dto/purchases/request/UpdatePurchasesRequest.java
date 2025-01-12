package com.example.outsourcing.dto.purchases.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdatePurchasesRequest{

    @NotNull
    private final Long purchasesId;

    @NotBlank
    private final String purchasesStatus;

    private UpdatePurchasesRequest(Long purchasesId, String purchasesStatus) {
        this.purchasesId = purchasesId;
        this.purchasesStatus = purchasesStatus;
    }

    @JsonCreator
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
