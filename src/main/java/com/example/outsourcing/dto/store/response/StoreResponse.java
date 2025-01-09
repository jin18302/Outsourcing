package com.example.outsourcing.dto.store.response;

import com.example.outsourcing.entity.Store;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@RequiredArgsConstructor
@Getter
public class StoreResponse {

    private final Long id;
    private final String name;
    private final String address;
    private final LocalTime open;
    private final LocalTime close;
    private final int minAmount;

    public static StoreResponse from(Store store) {
        return new StoreResponse(
                store.getId(),
                store.getName(),
                store.getAddress(),
                store.getOpen(),
                store.getClose(),
                store.getMinAmount()
        );
    }
}
