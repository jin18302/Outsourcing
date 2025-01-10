package com.example.outsourcing.dto.menu.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddMenuRequest(@JsonProperty("storeId")Long storeId,
                             @JsonProperty("name")String name,
                             @JsonProperty("price")Long price) {
}
