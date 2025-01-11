package com.example.outsourcing.dto.store.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record StoreUpdateRequest(
        String name,
        String address,
        String open,
        String close,
        Integer minAmount) {

    @JsonCreator
    public static StoreUpdateRequest from(
            @JsonProperty("name") String name,
            @JsonProperty("address") String address,
            @JsonProperty("open") String open,
            @JsonProperty("close") String close,
            @JsonProperty("minAmount") Integer minAmount) {
        return new StoreUpdateRequest(
                name,
                address,
                open,
                close,
                minAmount
        );
    }
}
