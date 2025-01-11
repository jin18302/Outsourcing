package com.example.outsourcing.dto.menu.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateMenuRequest{
    @NotBlank
    Long menuId;
    @NotBlank
    String name;
    @NotBlank
    Long price;

    private UpdateMenuRequest(Long menuId, String name, Long price) {
        this.menuId = menuId;
        this.name = name;
        this.price = price;
    }

    @JsonCreator
    public static UpdateMenuRequest from(
            @JsonProperty("menuId")
            Long menuId,
            @JsonProperty("name")
            String name,
            @JsonProperty("price")
            Long price
    ) {
        return new UpdateMenuRequest(
                menuId,
                name,
                price
        );
    }
}
