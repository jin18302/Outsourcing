package com.example.outsourcing.service.purchases;

import com.example.outsourcing.common.annotation.PurchasesLog;
import com.example.outsourcing.common.status.PurchasesStatus;
import com.example.outsourcing.dto.purchases.request.AddPurchasesRequest;
import com.example.outsourcing.dto.purchases.request.UpdatePurchasesRequest;
import com.example.outsourcing.dto.purchases.response.PurchasesResponse;
import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.entity.Purchases;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.entity.User;
import com.example.outsourcing.repository.menu.MenuConnector;
import com.example.outsourcing.repository.purchases.PurchasesConnector;
import com.example.outsourcing.repository.store.StoreConnector;
import com.example.outsourcing.repository.user.UserConnector;
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

    private final PurchasesConnector purchasesConnector;
    private final MenuConnector menuConnector;
    private final StoreConnector storeConnector;
    private final UserConnector userConnector;


    @PurchasesLog
    public PurchasesResponse createPurchases(AddPurchasesRequest request) {

        Store store = storeConnector.findById(request.storeId());

        LocalTime now = LocalTime.now();

        if (now.isBefore(store.getOpen())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "영업시작 전입니다");
        }

        if (now.isAfter(store.getClose())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "영업시간이 종료되었습니다");
        }

        Menu menu = menuConnector.findById(request.menuId());

        Long totalPrice = menu.getPrice();//

        if (totalPrice < store.getMinAmount()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "최소주문 금액을 만족하지 않아 주문 할 수 없습니다");
        }

        User user = userConnector.findById(request.userId());


        Purchases purchases = new Purchases(store, menu, totalPrice, user, PurchasesStatus.주문요청);

        Purchases savePurchases = purchasesConnector.save(purchases);

        return PurchasesResponse.from(savePurchases);

    }

    @PurchasesLog
    public PurchasesResponse cancelPurchasesByUsers(Long userId, Long purchasesId) {

        Purchases purchases = purchasesConnector.findById(purchasesId);

        Long ownerId = purchases.getUser().getId();

        if (!ownerId.equals(userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 존재하지 않습니다");
        }

        purchases.updateOrderStatus(PurchasesStatus.주문취소);

        return PurchasesResponse.from(purchases);
    }

    @PurchasesLog
    public PurchasesResponse changePurchasesByOwner(Long userId, UpdatePurchasesRequest request) {

        Purchases purchases = purchasesConnector.findById(request.purchasesId());
        Long ownerId = purchases.getStore().getUser().getId();

        if (!userId.equals(ownerId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 존재하지 않습니다");
        }

        PurchasesStatus status = PurchasesStatus.of(request.purchasesStatus());

        purchases.updateOrderStatus(status);

        return PurchasesResponse.from(purchases);
    }
}
