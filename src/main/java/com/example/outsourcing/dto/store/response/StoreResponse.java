package com.example.outsourcing.dto.store.response;

import com.example.outsourcing.entity.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;


public class StoreResponse {

    private final Long id;
    private final String name;
    private final String address;
    private final LocalTime open;
    private final LocalTime close;
    private final Integer minAmount;

    private StoreResponse(
            Long id,
            String name,
            String address,
            LocalTime open,
            LocalTime close,
            Integer minAmount
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.open = open;
        this.close = close;
        this.minAmount = minAmount;
    }

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
