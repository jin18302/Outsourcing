package com.example.outsourcing.dto.store.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class StoreRequest {

    private String name;
    private String address;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime open;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime close;

    private Integer minAmount;
}
