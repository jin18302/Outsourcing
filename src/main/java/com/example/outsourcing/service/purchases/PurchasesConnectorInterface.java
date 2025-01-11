package com.example.outsourcing.service.purchases;

import com.example.outsourcing.entity.Purchases;


public interface PurchasesConnectorInterface {

    Purchases save(Purchases purchases);

    Purchases findById(Long id);
}
