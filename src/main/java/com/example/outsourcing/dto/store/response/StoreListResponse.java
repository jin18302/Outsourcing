package com.example.outsourcing.dto.store.response;

import com.example.outsourcing.entity.Store;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class StoreListResponse{
    @JsonProperty("id")
    private final Long id;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("open")
    private final LocalTime open;
    @JsonProperty("close")
    private final LocalTime close;
    @JsonProperty("minAmount")
    private final Integer minAmount;

    private StoreListResponse(Long id, String name, LocalTime open, LocalTime close, Integer minAmount) {
        this.id = id;
        this.name = name;
        this.open = open;
        this.close = close;
        this.minAmount = minAmount;
    }

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
