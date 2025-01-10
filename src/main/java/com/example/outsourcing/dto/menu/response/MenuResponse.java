package com.example.outsourcing.dto.menu.response;

import com.example.outsourcing.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class MenuResponse {

    private final Long id;

    private final String name;

    private final Long price;


    public static MenuResponse from(Menu menu){
        return new MenuResponse(menu.getId(),menu.getName(), menu.getPrice());
    }
}
