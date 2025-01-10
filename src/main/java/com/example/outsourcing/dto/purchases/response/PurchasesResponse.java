package com.example.outsourcing.dto.purchases.response;


import com.example.outsourcing.entity.Purchases;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PurchasesResponse {

    private final Long purchasesId;

    private final Long storeId;

    private final Long menuId;

    private final Long userId;

    private final Long totalPrice;

    private final String purchasesStatus;


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
