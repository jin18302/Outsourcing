package com.example.outsourcing.entity;

import com.example.outsourcing.dto.menu.request.UpdateMenuRequest;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="menus")
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store")
    private Store store;

    @Column(name ="name")
    private  String name;

    @Column(name = "price")
    private  Integer price;


    public void update(UpdateMenuRequest updateMenuRequest){

    }
}
