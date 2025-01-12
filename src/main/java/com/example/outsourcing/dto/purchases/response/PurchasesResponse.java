package com.example.outsourcing.dto.purchases.response;


import com.example.outsourcing.entity.Purchases;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PurchasesResponse {

    @JsonProperty("purchasesId")
    private final Long purchasesId;
    @JsonProperty("storeId")
    private final Long storeId;
    @JsonProperty("menuId")
    private final Long menuId;
    @JsonProperty("userId")
    private final Long userId;
    @JsonProperty("totalPrice")
    private final Long totalPrice;
    @JsonProperty("purchasesStatus")
    private final String purchasesStatus;
    @JsonProperty("createdAt")
    private final LocalDateTime createdAt;
    @JsonProperty("modifiedAt")
    private final LocalDateTime modifiedAt;


    private PurchasesResponse(Long purchasesId, Long storeId, Long menuId, Long userId, Long totalPrice, String purchasesStatus, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.purchasesId = purchasesId;
        this.storeId = storeId;
        this.menuId = menuId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.purchasesStatus = purchasesStatus;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static PurchasesResponse from(Purchases purchases) {
        return new PurchasesResponse(
                purchases.getId(),
                purchases.getStore().getId(),
                purchases.getMenu().getId(),
                purchases.getUser().getId(),
                purchases.getTotalPrice(),
                purchases.getPurchasesStatus().toString(),
                purchases.getCreatedAt(),
                purchases.getModifiedAt()
        );
    }

}
