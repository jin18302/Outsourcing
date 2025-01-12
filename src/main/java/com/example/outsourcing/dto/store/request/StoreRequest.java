package com.example.outsourcing.dto.store.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class StoreRequest {
    @NotBlank
    private final String name;
    @NotBlank
    private final String address;
    @NotBlank
    private final String open;
    @NotBlank
    private final String close;
    @NotNull
    private final Integer minAmount;

    private StoreRequest(String name, String address, String open, String close, Integer minAmount) {
        this.name = name;
        this.address = address;
        this.open = open;
        this.close = close;
        this.minAmount = minAmount;
    }

    @JsonCreator
    public static StoreRequest from(
            @JsonProperty("name")
            String name,
            @JsonProperty("address")
            String address,
            @JsonProperty("open")
            String open,
            @JsonProperty("close")
            String close,
            @JsonProperty("minAmount")
            Integer minAmount
    ) {
        return new StoreRequest(
                name,
                address,
                open,
                close,
                minAmount
        );
    }
}
