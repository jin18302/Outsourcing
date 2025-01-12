package com.example.outsourcing.dto.purchases.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddPurchasesRequest{
    @NotNull
    private final Long storeId;
    @NotNull
    private final Long menuId;
    @NotNull
    private final Long userId;

    private AddPurchasesRequest(Long storeId, Long menuId, Long userId) {
        this.storeId = storeId;
        this.menuId = menuId;
        this.userId = userId;
    }

    @JsonCreator
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
