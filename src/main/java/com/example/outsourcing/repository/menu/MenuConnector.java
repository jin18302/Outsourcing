package com.example.outsourcing.repository.menu;

import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.service.menu.MenuConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuConnector implements MenuConnectorInterface {

    private final MenuRepository menuRepository;

    @Override
    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu findById(Long id) {
        return menuRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"해당 메뉴는 존재하지 않습니다"));
    }

    @Override
    public List<Menu> findByStoreId(Long storeId) {
        return menuRepository.findByStoreId(storeId);
    }
}
