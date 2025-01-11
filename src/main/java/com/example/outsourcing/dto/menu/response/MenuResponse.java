package com.example.outsourcing.dto.menu.response;

import com.example.outsourcing.entity.Menu;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MenuResponse {

    private final Long id;

    private final String name;

    @JsonProperty("price")
    private final Long price;

    private MenuResponse(Long id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @JsonCreator
    public static MenuResponse from(Menu menu){
        return new MenuResponse(
                menu.getId(),
                menu.getName(),
                menu.getPrice());
    }
}
