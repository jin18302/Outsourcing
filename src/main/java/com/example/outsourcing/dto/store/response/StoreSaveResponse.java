package com.example.outsourcing.dto.store.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StoreSaveResponse {

    private final Long id;
    private final String name;
}
