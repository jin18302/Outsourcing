package com.example.outsourcing.service.purchases;

import com.example.outsourcing.common.annotation.PurchasesLog;
import com.example.outsourcing.common.exception.InvalidRequestException;
import com.example.outsourcing.common.exception.UnauthorizedException;
import com.example.outsourcing.common.status.PurchasesStatus;
import com.example.outsourcing.dto.purchases.request.AddPurchasesRequest;
import com.example.outsourcing.dto.purchases.request.UpdatePurchasesRequest;
import com.example.outsourcing.dto.purchases.response.PurchasesResponse;
import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.entity.Purchases;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.entity.User;
import com.example.outsourcing.service.menu.MenuConnectorInterface;
import com.example.outsourcing.service.store.StoreConnectorInterface;
import com.example.outsourcing.service.user.UserConnectorInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@Transactional
@RequiredArgsConstructor
public class PurchasesService {

    private final PurchasesConnectorInterface PurchasesConnectorInterface;
    private final MenuConnectorInterface MenuConnectorInterface;
    private final StoreConnectorInterface StoreConnectorInterface;
    private final UserConnectorInterface UserConnectorInterface;

    @PurchasesLog
    public PurchasesResponse createPurchases(AddPurchasesRequest request, Long userId) {

        Store store = StoreConnectorInterface.findById(request.getStoreId());

        LocalTime now = LocalTime.now();

        if (now.isBefore(store.getOpen())) {
            throw new InvalidRequestException("영업시작 전입니다");
        }

        if (now.isAfter(store.getClose())) {
            throw new InvalidRequestException("영업시간이 종료되었습니다");
        }

        Menu menu = MenuConnectorInterface.findById(request.getMenuId());

        Long totalPrice = menu.getPrice();//

        if (totalPrice < store.getMinAmount()) {
            throw new InvalidRequestException("최소주문 금액을 만족하지 않아 주문 할 수 없습니다");
        }

        User user = UserConnectorInterface.findById(userId);

        Purchases purchases = new Purchases(store, menu, totalPrice, user, PurchasesStatus.주문요청);

        Purchases savePurchases = PurchasesConnectorInterface.save(purchases);

        return PurchasesResponse.from(savePurchases);

    }

    @PurchasesLog
    public PurchasesResponse cancelPurchasesByUsers(Long userId, Long purchasesId) {

        Purchases purchases = PurchasesConnectorInterface.findById(purchasesId);

        Long ownerId = purchases.getUser().getId();

        if (!ownerId.equals(userId)) {
            throw new UnauthorizedException("권한이 존재하지 않습니다");
        }
        purchases.updateOrderStatus(PurchasesStatus.주문취소);

        return PurchasesResponse.from(purchases);
    }


    @PurchasesLog
    public PurchasesResponse changePurchasesByOwner(Long userId, UpdatePurchasesRequest request) {

        Purchases purchases = PurchasesConnectorInterface.findById(request.getPurchasesId());
        Long ownerId = purchases.getStore().getUser().getId();

        if (!userId.equals(ownerId)) {
            throw new UnauthorizedException("권한이 존재하지 않습니다");
        }

        PurchasesStatus status = PurchasesStatus.of(request.getPurchasesStatus());

        purchases.updateOrderStatus(status);

        return PurchasesResponse.from(purchases);
    }
}
