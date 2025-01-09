package com.example.outsourcing.dto.menu.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddMenuRequest {

    private Long storeId;

    private String name;

    private Long price;

}
