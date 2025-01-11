package com.example.outsourcing.dto.store.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record StoreRequest(
        @JsonProperty("name") String name,
        @JsonProperty("address") String address,
        @JsonProperty("open") String open,
        @JsonProperty("close") String close,
        @JsonProperty("minAmount") Integer minAmount) {
}
