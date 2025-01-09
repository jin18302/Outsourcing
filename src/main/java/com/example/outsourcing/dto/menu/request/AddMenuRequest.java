package com.example.outsourcing.dto.menu.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
public class AddMenuRequest {

    private Long storeId;

    private String name;

    private Long price;


    @JsonCreator
    public AddMenuRequest(@JsonProperty("storeId")Long storeId,
                          @JsonProperty("name")String name,
                          @JsonProperty("price")Long price){

        this.storeId = storeId;
        this.name = name;
        this.price = price;
    }

}
