package com.example.outsourcing.service.menu;

import com.example.outsourcing.dto.search.response.SearchResponse;
import com.example.outsourcing.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuConnectorInterface {

    Menu save(Menu menu);

    Menu findById(Long id);

    List<Menu> findByStoreId(Long storeId);
}
