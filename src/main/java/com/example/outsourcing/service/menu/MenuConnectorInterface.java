package com.example.outsourcing.service.menu;

import com.example.outsourcing.entity.Menu;

import java.util.List;

public interface MenuConnectorInterface {

    Menu save(Menu menu);

    Menu findById(Long id);

    List<Menu> findByStoreId(Long storeId);
}
