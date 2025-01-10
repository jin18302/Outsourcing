package com.example.outsourcing.service.store;

import com.example.outsourcing.entity.Store;
import com.example.outsourcing.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
public class RepositoryConnectService {

    private final StoreRepository storeRepository;


    public Store getStoreById(Long storeId) {
        return storeRepository.findByIdAndIsDeletedFalse(storeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"가게를 찾을 수 없습니다."));
    }
}
