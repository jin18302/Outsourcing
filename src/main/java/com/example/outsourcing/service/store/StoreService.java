package com.example.outsourcing.service.store;

import com.example.outsourcing.dto.store.request.StoreRequest;
import com.example.outsourcing.dto.store.request.StoreUpdateRequest;
import com.example.outsourcing.dto.store.response.StoreListResponse;
import com.example.outsourcing.dto.store.response.StoreResponse;
import com.example.outsourcing.dto.store.response.StoreSaveResponse;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.entity.User;
import com.example.outsourcing.repository.store.StoreRepository;
import com.example.outsourcing.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class StoreService {

    private final UserService userService;
    private final StoreRepository storeRepository;

    @Transactional
    public StoreSaveResponse create(Long userId, StoreRequest request) {

        int storeCount = storeRepository.countByUserId(userId);

        if(storeCount == 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "가게는 최대 3개까지만 운영할 수 있습니다.");
        }

        User user = userService.getUserById(userId);

        Store newStore = new Store(
                user,
                request.name(),
                request.address(),
                request.open(),
                request.close(),
                request.minAmount()
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
        Page<Store> storeList = storeRepository.findAllByName(name, pageable);

        return storeList.map(StoreListResponse::from);
    }

    public List<StoreListResponse> findMyStore(Long userId) {
        return storeRepository.findMyStoresByUserId(userId)
                .stream()
                .map(StoreListResponse::from)
                .toList();
    }

    @Transactional
    public void updateStore(Long userId, Long storeId, StoreUpdateRequest request) {

        Store store = getStoreById(storeId);

        if(!Objects.equals(store.getUser().getId(), userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "본인의 가게만 수정할 수 있습니다.");
        }

        store.updateDetails(
                request.name(),
                request.address(),
                request.open(),
                request.close(),
                request.minAmount()
        );
    }

    @Transactional
    public void deleteStore(Long userId, Long storeId) {

        Store store = getStoreById(storeId);

        if(!Objects.equals(store.getUser().getId(), userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "본인의 가게만 삭제할 수 있습니다.");
        }

        store.delete();
    }

    public Store getStoreById(Long storeId) {
        return storeRepository.findByIdAndIsDeletedFalse(storeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"가게를 찾을 수 없습니다."));
    }
}
