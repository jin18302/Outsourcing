package com.example.outsourcing.service.purchases;

import com.example.outsourcing.entity.Purchases;
import com.example.outsourcing.repository.purchases.PurchasesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;


@Component
@RequiredArgsConstructor
public class PurchasesRepositoryConnector {

    private final PurchasesRepository purchasesRepository;

    public Purchases savePurchases(Purchases purchases){
        return purchasesRepository.save(purchases);
    }

    public Purchases findPurchasesById(Long id){
        return purchasesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 주문은 존재하지 않습니다"));
    }


}
