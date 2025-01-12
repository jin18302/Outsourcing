package com.example.outsourcing.dto.menu.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddMenuRequest{

    @NotNull
    Long storeId;
    @NotBlank
    String name;
    @NotNull
    Long price;

    private AddMenuRequest(Long storeId, String name, Long price) {
        this.storeId = storeId;
        this.name = name;
        this.price = price;
    }

    @JsonCreator
    public static AddMenuRequest from(
            @JsonProperty("storeId")
            Long storeId,
            @JsonProperty("name")
            String name,
            @JsonProperty("price")
            Long price
    ) {
        return new AddMenuRequest(
                storeId,
                name,
                price
        );
    }
}
