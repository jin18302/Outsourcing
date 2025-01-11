package com.example.outsourcing.dto.purchases.response;


import com.example.outsourcing.entity.Purchases;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class PurchasesResponse {

    private final Long purchasesId;

    private final Long storeId;

    private final Long menuId;

    private final Long userId;

    private final Long totalPrice;

    private final String purchasesStatus;

    private PurchasesResponse(Long purchasesId, Long storeId, Long menuId, Long userId, Long totalPrice, String purchasesStatus) {
        this.purchasesId = purchasesId;
        this.storeId = storeId;
        this.menuId = menuId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.purchasesStatus = purchasesStatus;
    }

    public static PurchasesResponse from(Purchases purchases) {
        return new PurchasesResponse(
                purchases.getId(),
                purchases.getStore().getId(),
                purchases.getMenu().getId(),
                purchases.getUser().getId(),
                purchases.getTotalPrice(),
                purchases.getPurchasesStatus().name());
    }
}
