package com.example.outsourcing.dto.order.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddOrderRequest {

//    private Long storeId;

    private Long menuId;

    private Long userId;
}
