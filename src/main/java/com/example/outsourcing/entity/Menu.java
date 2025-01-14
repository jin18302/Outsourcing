package com.example.outsourcing.entity;


import com.example.outsourcing.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity @Table(name ="menus")
@NoArgsConstructor
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "is_delete")
    private boolean isDelete = false;


    public void updateName(String name) {
        this.name = name;
    }

    public void updatePrice(Long price) {
        this.price = price;
    }

    public void delete() {
        this.isDelete = true;
    }


    private Menu(Store store, String name, Long price) {
        this.store = store;
        this.name = name;
        this.price = price;
    }

    public static Menu from(Store store, String name, Long price) {
        return new Menu(store, name, price);
    }
}
