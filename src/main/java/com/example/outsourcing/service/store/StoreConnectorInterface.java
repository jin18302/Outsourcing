package com.example.outsourcing.service.store;

import com.example.outsourcing.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StoreConnectorInterface {

    Store findById(Long storeId);

    List<Store> findMyStoresByUserId(Long userId);

    int countByUserId(Long userId);

    Store save(Store newStore);

    Page<Store> findByIsDeletedFalse(Pageable pageable);

    Page<Store> findAllByName(String name, Pageable pageable);

    Optional<Object> findByIdAndIsDeletedFalse(Long storeId);
}
