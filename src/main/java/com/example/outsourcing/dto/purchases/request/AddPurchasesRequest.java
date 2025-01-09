package com.example.outsourcing.dto.purchases.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class AddPurchasesRequest {

    private Long storeId;

    private Long menuId;

    private Long userId;

    @JsonCreator
    public AddPurchasesRequest(@JsonProperty("storeId")Long storeId,
                               @JsonProperty("menuId")Long menuId,
                               @JsonProperty("userId")Long userId){

        this.storeId = storeId;
        this.menuId = menuId;
        this.userId = userId;
    }
}
