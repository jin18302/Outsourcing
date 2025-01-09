package com.example.outsourcing.service.store;

import com.example.outsourcing.dto.store.request.StoreRequest;
import com.example.outsourcing.dto.store.request.StoreUpdateRequest;
import com.example.outsourcing.dto.store.response.StoreListResponse;
import com.example.outsourcing.dto.store.response.StoreResponse;
import com.example.outsourcing.dto.store.response.StoreSaveResponse;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreSaveResponse create(Long userId, StoreRequest request) {

        int storeCount = storeRepository.countByUserId(userId);

        if(storeCount > 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "가게는 최대 3개까지만 운영할 수 있습니다.");
        }

        Store newStore = new Store(
                request.getName(),
                request.getAddress(),
                request.getOpen(),
                request.getClose(),
                request.getMinAmount()
        );
        Store savedStore = storeRepository.save(newStore);

        return new StoreSaveResponse(savedStore.getId(), savedStore.getName());
    }

    public StoreResponse findById(Long storeId) {
        Store store = getStoreById(storeId);
        return StoreResponse.from(store);
    }

    public Page<StoreListResponse> findAll(int page, int size) {

        Pageable pageable = PageRequest.of(page-1, size);
        Page<Store> storeList = storeRepository.findByIsDeletedFalse(pageable);

        return storeList.map(store -> new StoreListResponse(
                store.getId(),
                store.getName(),
                store.getOpen(),
                store.getClose(),
                store.getMinAmount()));
    }


    public Page<StoreListResponse> findByName(String name, int page, int size) {

        Pageable pageable = PageRequest.of(page-1, size);
        Page<Store> storeList = storeRepository.findByName(name, pageable);

        return storeList.map(StoreListResponse::from);
    }

    public List<StoreListResponse> findMyStore(Long userId) {
        return storeRepository.findByUserId(userId)
                .stream()
                .map(StoreListResponse::from)
                .toList();
    }

    public void updateStore(Long userId, Long storeId, StoreUpdateRequest request) {

        Store store = getStoreById(storeId);

        if(store.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"폐업한 가게입니다.");
        }

        if(!Objects.equals(store.getUser().getId(), userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "본인의 가게만 수정할 수 있습니다.");
        }

        if (request.getName() != null) {
            store.updateName(request.getName());
        }

        if (request.getAddress() != null) {
            store.updateAddress(request.getAddress());
        }

        if (request.getAddress() != null) {
            store.updateOpen(request.getOpen());
        }

        if (request.getAddress() != null) {
            store.updateClose(request.getClose());
        }

        if (request.getAddress() != null) {
            store.updateMinAmount(request.getMinAmount());
        }
    }

    public void deleteStore(Long userId, Long storeId) {

        Store store = getStoreById(storeId);

        if(store.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"폐업한 가게입니다.");
        }

        if(!Objects.equals(store.getUser().getId(), userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "본인의 가게만 수정할 수 있습니다.");
        }

        store.delete();
    }

    public Store getStoreById(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"가게를 찾을 수 없습니다."))
    }


}
