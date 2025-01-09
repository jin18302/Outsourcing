package com.example.outsourcing.entity;

import com.example.outsourcing.common.status.PurchasesStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "purchases")
@NoArgsConstructor
public class Purchases {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private PurchasesStatus orderStatus;

    @Column(name = "total_price")
    private Long totalPrice;


    public Purchases(Store store, Menu menu, Long price, User user, PurchasesStatus orderStatus){
        this.store = store;
        this.menu = menu;
        this.totalPrice = price;
        this.user = user;
        this.orderStatus = orderStatus;
    }

    public void updateOrderStatus(PurchasesStatus orderStatus){
        this.orderStatus = orderStatus;
    }
}
