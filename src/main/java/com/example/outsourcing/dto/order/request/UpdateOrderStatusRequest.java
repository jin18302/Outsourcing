package com.example.outsourcing.dto.order.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderStatusRequest {

    private String orderStatus;

    private Long purchasesId;
}
