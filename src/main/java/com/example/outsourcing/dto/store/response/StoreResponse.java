package com.example.outsourcing.dto.store.response;

import com.example.outsourcing.entity.Store;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class StoreResponse {
    @JsonProperty("id")
    private final Long id;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("address")
    private final String address;
    @JsonProperty("open")
    private final LocalTime open;
    @JsonProperty("close")
    private final LocalTime close;
    @JsonProperty("minAmount")
    private final Integer minAmount;
    @JsonProperty("createdAt")
    private final LocalDateTime createdAt;
    @JsonProperty("modifiedAt")
    private final LocalDateTime modifiedAt;

    private StoreResponse(Long id, String name, String address, LocalTime open, LocalTime close, Integer minAmount, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.open = open;
        this.close = close;
        this.minAmount = minAmount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static StoreResponse from(Store store) {
        return new StoreResponse(
                store.getId(),
                store.getName(),
                store.getAddress(),
                store.getOpen(),
                store.getClose(),
                store.getMinAmount(),
                store.getCreatedAt(),
                store.getModifiedAt()
        );
    }
}
