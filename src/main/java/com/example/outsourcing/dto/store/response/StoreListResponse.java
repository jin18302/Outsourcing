package com.example.outsourcing.dto.store.response;

import com.example.outsourcing.entity.Store;

import java.time.LocalTime;

public record StoreListResponse(
        Long id,
        String name,
        LocalTime open,
        LocalTime close,
        Integer minAmount) {

    public static StoreListResponse from(Store store) {
        return new StoreListResponse(
                store.getId(),
                store.getName(),
                store.getOpen(),
                store.getClose(),
                store.getMinAmount()
        );
    }
}
