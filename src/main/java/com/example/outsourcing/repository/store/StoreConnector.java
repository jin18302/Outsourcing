package com.example.outsourcing.repository.store;

import com.example.outsourcing.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class StoreConnector {

    private final StoreRepository storeRepository;

    public Store findById(Long storeId) {
        return storeRepository.findByIdAndIsDeletedFalse(storeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"가게없음"));
    }
}
