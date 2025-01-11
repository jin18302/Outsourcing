package com.example.outsourcing.dto.purchases.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class AddPurchasesRequest{
    @NotBlank
    Long storeId;
    @NotBlank
    Long menuId;
    @NotBlank
    Long userId;

    private AddPurchasesRequest(Long storeId, Long menuId, Long userId) {
        this.storeId = storeId;
        this.menuId = menuId;
        this.userId = userId;
    }

    public static AddPurchasesRequest from(
            @JsonProperty("storeId")
            Long storeId,
            @JsonProperty("menuId")
            Long menuId,
            @JsonProperty("userId")
            Long userId
    ) {
        return new AddPurchasesRequest(
                storeId,
                menuId,
                userId);
    }
}
