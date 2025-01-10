package com.example.outsourcing.dto.menu.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateMenuRequest(@JsonProperty("menuId")Long menuId,
                                @JsonProperty("name")String name,
                                @JsonProperty("price")Long price) {
}
