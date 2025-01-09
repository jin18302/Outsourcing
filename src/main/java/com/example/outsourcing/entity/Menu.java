package com.example.outsourcing.entity;

import com.example.outsourcing.dto.menu.request.UpdateMenuRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity @Table(name ="menus")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name ="name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "is_delete")
    private boolean isDelete = false;


    public Menu(Store store, String name, Long price){
        this.store = store;
        this.name = name;
        this.price = price;
    }


    public void update(String name, Long price){
        this.name = name;
        this.price = price;
    }

    public void delete(){
        this.isDelete = true;
    }
}
