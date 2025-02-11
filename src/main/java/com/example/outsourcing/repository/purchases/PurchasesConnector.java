package com.example.outsourcing.repository.purchases;

import com.example.outsourcing.entity.Purchases;
import com.example.outsourcing.service.purchases.PurchasesConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PurchasesConnector implements PurchasesConnectorInterface {

    private final PurchasesRepository purchasesRepository;

    @Override
    public Purchases save(Purchases purchases){
        return purchasesRepository.save(purchases);
    }

    @Override
    public Purchases findById(Long id){
        return purchasesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 주문은 존재하지 않습니다"));
    }

    @Override
    public List<Purchases> findAllByStoreId(Long storeId) {
        return purchasesRepository.findAllByStoreId(storeId);
    }

    @Override
    public List<Purchases> findAllByUserId(Long userId) {
        return purchasesRepository.findAllByUserId(userId);
    }


}
