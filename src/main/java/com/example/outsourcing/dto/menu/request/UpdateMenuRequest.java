package com.example.outsourcing.dto.menu.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UpdateMenuRequest {

    private Long menuId;

    private String name;

    private Long price;

    @JsonCreator
    public UpdateMenuRequest(@JsonProperty("menuId")Long menuId,
                             @JsonProperty("name")String name,
                             @JsonProperty("price")Long price){

        this.menuId = menuId;
        this.name = name;
        this.price = price;
    }
}
