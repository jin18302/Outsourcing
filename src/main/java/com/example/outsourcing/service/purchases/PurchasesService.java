package com.example.outsourcing.service.purchases;

import com.example.outsourcing.common.OrderStatus;
import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.entity.Purchases;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.entity.User;
import com.example.outsourcing.repository.purchases.PurchasesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;

//- 사장님은 주문을 수락할 수 있으며, 배달이 완료될 때까지의 모든 상태를 순서대로 변경 합니다.
//        - 주문 요청 및 상태 변경
//    - 새로운 주문이거나 주문의 상태가 변경될 때는 AOP에 의해 로그를 남겨야합니다.
//        - 로그에는 `요청 시각`, `가게 id`, `주문 id`가 필수로 포함되어야합니다.
//        - 가게에서 설정한 최소 주문 금액을 만족해야 주문이 가능합니다.
//        - 가게의 오픈/마감 시간이 지나면 주문할 수 없습니다.

@Service
@Transactional
@RequiredArgsConstructor
public class PurchasesService {

    @PersistenceContext
    private EntityManager em;

    private final PurchasesRepository purchasesRepository;

    public void createOrder(Long storeId, Long userId, Long menuId) {
        Store store = em.find(Store.class, storeId);

        LocalTime now = LocalTime.now();

        if (now.isBefore(store.getOpen())) {
            throw new RuntimeException("영업시작 전 입니다");
        }

        if (now.isAfter(store.getClose())) {
            throw new RuntimeException("영업시간이 종료되었습니다");
        }

        Menu menu = em.find(Menu.class, menuId);

        Long totalPrice = menu.getPrice();

        if (totalPrice < store.getMinAmount()) {
            throw new RuntimeException("최소주문 금액을 만족하지 않아 주문 할 수 없습니다");
        }

        User user = em.find(User.class, userId);

        Purchases purchases = new Purchases(store, menu, totalPrice, user, OrderStatus.주문요청);

        purchasesRepository.save(purchases);

    }

    public void cancelOrder(Long userId, Long purchasesId) {
        /*
        TODO: 해당주문이 존재하는 지 확인한다
               주문에서 주문자의 id정보를 빼온다
               주문을 취소하고자하는 유저와 주문을 요청했던 사용자가 같은지 확인한다
               일치하지않는다면 예외를 발생시킨다
               일치한다면 상태를 취소로 변경한다
         */

        Purchases purchases = purchasesRepository.findById(purchasesId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 주문은 존재하지 않습니다"));

        Long ownerId = purchases.getUser().getId();

        if (ownerId.equals(userId)) {
            purchases.updateOrderStatus(OrderStatus.주문취소);
        }
    }

    public void orderStatusChange(Long userId, Long purchasesId, String orderStatus) {
        /*
        TODO: 받아온 오더가 존재하는지 확인한다-> 확인하지 않는다면 예외를 발생시킨다
            오더가 존재한다면 해당오더의 메뉴, 메뉴에서 가게, 가게에서 사장을 뽑아온다
            주문수락 설정을 하고자하는 유저가 해당오더의 대한 가게사장이 맞는지 확인한다-> 일치하지 않는다면 예외를 발생시킨다
            일치한다면 해당 오더에 대한 상태를 주문수락으로 바꾼다
         */

        Purchases purchases = purchasesRepository.findById(purchasesId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 주문은 존재하지 않습니다"));

        Long ownerId = purchases.getStore().getUser().getId();

        if(!userId.equals(ownerId)){
            throw new RuntimeException("권한이 존재하지 않습니다");
        }

        OrderStatus status = OrderStatus.of(orderStatus);

        purchases.updateOrderStatus(status);
    }
}
