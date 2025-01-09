package com.example.outsourcing.dto.order.response;


import com.example.outsourcing.entity.Menu;
import lombok.Getter;

@Getter
public class OrderResponse {
    private Long id;

    private Long storeId;

    private Long menuId;
}
