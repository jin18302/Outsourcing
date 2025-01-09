package com.example.outsourcing.dto.menu.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMenuRequest {

    private Long menuId;

    private String name;

    private Long price;
}
