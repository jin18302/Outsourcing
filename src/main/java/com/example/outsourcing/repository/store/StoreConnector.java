package com.example.outsourcing.repository.store;

import com.example.outsourcing.common.exception.NotFoundException;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.service.store.StoreConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StoreConnector implements StoreConnectorInterface{

    private final StoreRepository storeRepository;

    @Override
    public Store findById(Long storeId) {
        return storeRepository.findByIdAndIsDeletedFalse(storeId)
                .orElseThrow(() -> new NotFoundException("가게없음"));
    }

    @Override
    public List<Store> findMyStoresByUserId(Long userId) {
        return storeRepository.findMyStoresByUserId(userId);
    }

    @Override
    public int countByUserId(Long userId) {
        return storeRepository.countByUserId(userId);
    }

    @Override
    public Store save(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Page<Store> findByIsDeletedFalse(Pageable pageable) {
        return storeRepository.findByIsDeletedFalse(pageable);
    }

    @Override
    public Page<Store> findAllByName(String name, Pageable pageable) {
        return storeRepository.findAllByName(name, pageable);
    }

    @Override
    public Optional<Object> findByIdAndIsDeletedFalse(Long storeId) {
        return Optional.empty();
    }

}
