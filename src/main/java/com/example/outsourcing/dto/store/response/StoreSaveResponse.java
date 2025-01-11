package com.example.outsourcing.dto.store.response;

import com.example.outsourcing.entity.Store;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class StoreSaveResponse{
    @JsonProperty("id")
    private final Long id;
    @JsonProperty("name")
    private final String name;

    private StoreSaveResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static StoreSaveResponse from(Store savedStore) {
        return new StoreSaveResponse(
                savedStore.getId(),
                savedStore.getName()
        );
    }
}
