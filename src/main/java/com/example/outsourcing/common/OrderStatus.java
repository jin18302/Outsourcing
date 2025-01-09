package com.example.outsourcing.common;

import java.util.Arrays;

public enum OrderStatus {

    주문요청, 주문수락, 주문취소, 배달완료;

    public static OrderStatus of(String status) {
        return Arrays.stream(OrderStatus.values())//이넘의 값을 뽑는다
                .filter(r -> r.name().equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("지원하지 않는 기능입니다"));
    }
}
