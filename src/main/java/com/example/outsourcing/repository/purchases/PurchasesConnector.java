package com.example.outsourcing.repository.purchases;

import com.example.outsourcing.entity.Purchases;
import com.example.outsourcing.service.purchases.PurchasesConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class PurchasesConnector implements PurchasesConnectorInterface {

    private final PurchasesRepository purchasesRepository;

    public Purchases save(Purchases purchases){
        return purchasesRepository.save(purchases);
    }

    public Purchases findById(Long id){
        return purchasesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 주문은 존재하지 않습니다"));
    }
}
