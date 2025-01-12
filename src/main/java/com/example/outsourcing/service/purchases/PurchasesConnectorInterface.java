package com.example.outsourcing.service.purchases;

import com.example.outsourcing.entity.Purchases;

import java.util.List;


public interface PurchasesConnectorInterface {

    Purchases save(Purchases purchases);

    Purchases findById(Long id);

    List<Purchases> findAllByStoreId(Long storeId);

    List<Purchases> findAllByUserId(Long userId);


}
