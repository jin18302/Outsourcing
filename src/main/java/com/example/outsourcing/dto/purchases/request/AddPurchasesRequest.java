package com.example.outsourcing.dto.purchases.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddPurchasesRequest(@JsonProperty("storeId")Long storeId,
                                  @JsonProperty("menuId")Long menuId,
                                  @JsonProperty("userId")Long userId) {


}
