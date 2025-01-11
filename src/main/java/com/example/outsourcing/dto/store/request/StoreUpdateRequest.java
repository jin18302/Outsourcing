package com.example.outsourcing.dto.store.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class StoreUpdateRequest{

    private final String name;
    private final String address;
    private final String open;
    private final String close;
    private final Integer minAmount;

    private StoreUpdateRequest(String name, String address, String open, String close, Integer minAmount) {
        this.name = name;
        this.address = address;
        this.open = open;
        this.close = close;
        this.minAmount = minAmount;
    }

    @JsonCreator
    public static StoreUpdateRequest from(
            @JsonProperty("name")
            String name,
            @JsonProperty("address")
            String address,
            @JsonProperty("open")
            String open,
            @JsonProperty("close")
            String close,
            @JsonProperty("minAmount")
            Integer minAmount) {
        return new StoreUpdateRequest(
                name,
                address,
                open,
                close,
                minAmount
        );
    }
}
