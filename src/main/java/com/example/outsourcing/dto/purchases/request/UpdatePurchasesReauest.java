package com.example.outsourcing.dto.purchases.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdatePurchasesReauest(@JsonProperty("purchasesId")Long purchasesId,
                                     @JsonProperty("purchasesStatus")String purchasesStatus) {
}
