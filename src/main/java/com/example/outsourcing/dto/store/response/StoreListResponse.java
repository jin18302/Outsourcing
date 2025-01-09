package com.example.outsourcing.dto.store.response;

import com.example.outsourcing.entity.Store;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
public class StoreListResponse {

    private final Long id;
    private final String name;
    private final LocalTime open;
    private final LocalTime close;
    private final int minAmount;

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
