package com.example.outsourcing.dto.store.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record StoreRequest(
        String name,
        String address,
        String open,
        String close,
        Integer minAmount) {

    @JsonCreator
    public StoreRequest(
            @JsonProperty("name") String name,
            @JsonProperty("address") String address,
            @JsonProperty("open") String open,
            @JsonProperty("close") String close,
            @JsonProperty("minAmount") Integer minAmount
    ) {
        this.name = name;
        this.address = address;
        this.open = open;
        this.close = close;
        this.minAmount = minAmount;
    }
}
