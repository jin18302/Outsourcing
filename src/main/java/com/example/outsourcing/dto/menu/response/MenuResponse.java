package com.example.outsourcing.dto.menu.response;

import com.example.outsourcing.entity.Menu;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class MenuResponse {
    @JsonProperty("id")
    private final Long id;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("price")
    private final Long price;
    @JsonProperty("createdAt")
    private final LocalDateTime createdAt;
    @JsonProperty("modifiedAt")
    private final LocalDateTime modifiedAt;

    private MenuResponse(Long id, String name, Long price, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }


    public static MenuResponse from(Menu menu){
        return new MenuResponse(
                menu.getId(),
                menu.getName(),
                menu.getPrice(),
                menu.getCreatedAt(),
                menu.getModifiedAt());
    }
}
