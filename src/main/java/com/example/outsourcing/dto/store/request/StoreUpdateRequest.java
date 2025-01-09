package com.example.outsourcing.dto.store.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class StoreUpdateRequest {

    private String name;
    private String address;

    @JsonFormat(pattern = "HH:mm")
    private String open;

    @JsonFormat(pattern = "HH:mm")
    private String close;

    private Integer minAmount;

    @JsonCreator
    public StoreUpdateRequest(
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
