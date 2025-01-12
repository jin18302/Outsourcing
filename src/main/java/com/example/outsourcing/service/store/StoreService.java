package com.example.outsourcing.service.store;

import com.example.outsourcing.common.exception.InvalidRequestException;
import com.example.outsourcing.common.exception.UnauthorizedException;
import com.example.outsourcing.dto.store.request.StoreRequest;
import com.example.outsourcing.dto.store.request.StoreUpdateRequest;
import com.example.outsourcing.dto.store.response.StoreListResponse;
import com.example.outsourcing.dto.store.response.StoreResponse;
import com.example.outsourcing.dto.store.response.StoreSaveResponse;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.entity.User;
import com.example.outsourcing.service.user.UserConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class StoreService {

    private final UserConnectorInterface userConnectorInterface;
    private final StoreConnectorInterface storeConnectorInterface;

    @Transactional
    public StoreResponse create(Long userId, StoreRequest request) {

        int storeCount = storeConnectorInterface.countByUserId(userId);

        if(storeCount == 3) {
            throw new InvalidRequestException("가게는 최대 3개까지만 운영할 수 있습니다.");
        }

        User user = userConnectorInterface.findById(userId);

        Store newStore = Store.from(
                user,
                request.getName(),
                request.getAddress(),
                request.getOpen(),
                request.getClose(),
                request.getMinAmount());
        Store savedStore = storeConnectorInterface.save(newStore);

        return StoreResponse.from(savedStore);
    }

    public StoreResponse findById(Long storeId) {
        Store store =  storeConnectorInterface.findById(storeId);
        return StoreResponse.from(store);
    }

    public Page<StoreListResponse> findAll(int page, int size) {

        Pageable pageable = PageRequest.of(page-1, size);
        Page<Store> storeList = storeConnectorInterface.findByIsDeletedFalse(pageable);

        return storeList.map(StoreListResponse::from);
    }


    public Page<StoreListResponse> findByName(String name, int page, int size) {

        Pageable pageable = PageRequest.of(page-1, size);
        Page<Store> storeList = storeConnectorInterface.findAllByName(name, pageable);

        return storeList.map(StoreListResponse::from);
    }

    public List<StoreListResponse> findMyStore(Long userId) {
        return storeConnectorInterface.findMyStoresByUserId(userId)
                .stream()
                .map(StoreListResponse::from)
                .toList();
    }

    @Transactional
    public StoreResponse updateStore(Long userId, Long storeId, StoreUpdateRequest request) {

        Store store = storeConnectorInterface.findById(storeId);

        if(!Objects.equals(store.getUser().getId(), userId)) {
            throw new UnauthorizedException("본인의 가게만 수정할 수 있습니다.");
        }

        store.updateDetails(
                request.getName(),
                request.getAddress(),
                request.getOpen(),
                request.getClose(),
                request.getMinAmount()
        );

        return StoreResponse.from(store);
    }

    @Transactional
    public void deleteStore(Long userId, Long storeId) {

        Store store = storeConnectorInterface.findById(storeId);

        if(!Objects.equals(store.getUser().getId(), userId)) {
            throw new UnauthorizedException("본인의 가게만 수정할 수 있습니다.");
        }

        store.delete();
    }

}
