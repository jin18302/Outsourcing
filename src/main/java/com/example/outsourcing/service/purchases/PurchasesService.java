package com.example.outsourcing.service.purchases;

import com.example.outsourcing.common.annotation.PurchasesLog;
import com.example.outsourcing.common.status.PurchasesStatus;
import com.example.outsourcing.dto.purchases.request.AddPurchasesRequest;
import com.example.outsourcing.dto.purchases.response.PurchasesResponse;
import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.entity.Purchases;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.entity.User;
import com.example.outsourcing.repository.menu.MenuRepository;
import com.example.outsourcing.repository.purchases.PurchasesRepository;
import com.example.outsourcing.repository.store.StoreRepository;
import com.example.outsourcing.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;

@Service
@Transactional
@RequiredArgsConstructor
public class PurchasesService {

    private final PurchasesRepository purchasesRepository;
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;


    @PurchasesLog
    public PurchasesResponse createPurchases(AddPurchasesRequest request) {

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당가게는 존재하지 않습니다"));

        LocalTime now = LocalTime.now();

        if (now.isBefore(store.getOpen())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "영업시작 전입니다");
        }

        if (now.isAfter(store.getClose())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "영업시간이 종료되었습니다");
        }

        Menu menu = menuRepository.findById(request.getMenuId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"헤당 메뉴는 존재하지 않습니다"));

        Long totalPrice = menu.getPrice();//

        if (totalPrice < store.getMinAmount()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "최소주문 금액을 만족하지 않아 주문 할 수 없습니다");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저는 존재하지 않습니다"));


        Purchases purchases = new Purchases(store, menu, totalPrice, user, PurchasesStatus.주문요청);

        Purchases savePurchases = purchasesRepository.save(purchases);

        return new PurchasesResponse(savePurchases);

    }

    @PurchasesLog
    public PurchasesResponse cancelPurchases(Long userId, Long purchasesId) {

        Purchases purchases = purchasesRepository.findById(purchasesId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 주문은 존재하지 않습니다"));

        Long ownerId = purchases.getUser().getId();

        if (!ownerId.equals(userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 존재하지 않습니다");
        }

        purchases.updateOrderStatus(PurchasesStatus.주문취소);

        return new PurchasesResponse(purchases);
    }

    @PurchasesLog
    public void purchasesStatusChange(Long userId, Long purchasesId, String purchasesStatus) {

        Purchases purchases = purchasesRepository.findById(purchasesId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 주문은 존재하지 않습니다"));

        Long ownerId = purchases.getStore().getUser().getId();

        if (!userId.equals(ownerId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 존재하지 않습니다");
        }

        PurchasesStatus status = PurchasesStatus.of(purchasesStatus);

        purchases.updateOrderStatus(status);
    }
}
