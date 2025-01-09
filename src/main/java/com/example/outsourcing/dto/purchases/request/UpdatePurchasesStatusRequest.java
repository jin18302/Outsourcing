package com.example.outsourcing.dto.purchases.request;

import com.example.outsourcing.dto.review.request.UpdateReviewRequest;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UpdatePurchasesStatusRequest {

    private String purchasesStatus;


    @JsonCreator
    public UpdatePurchasesStatusRequest(@JsonProperty("purchasesStatus")String purchasesStatus){
        this.purchasesStatus = purchasesStatus;
    }
}
