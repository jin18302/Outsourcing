package com.example.outsourcing.dto.menu.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuResponse {

    private Long id;

    private String name;

    private Long price;

    public MenuResponse(String name, Long price){
        this.name = name;
        this.price = price;
    }
}
