package com.example.outsourcing.dto.purchases.response;


import com.example.outsourcing.entity.Purchases;
import lombok.Getter;

@Getter
public class PurchasesResponse {

    private Long purchasesId;

    private Long storeId;


    public PurchasesResponse(Purchases purchases) {
        this.purchasesId = purchases.getId();
        this.storeId = purchases.getStore().getId();
    }
}
