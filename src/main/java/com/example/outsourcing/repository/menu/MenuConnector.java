package com.example.outsourcing.repository.menu;

import com.example.outsourcing.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuConnector {
    private final MenuRepository menuRepository;

    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu findById(Long id) {
        return menuRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"해당 메뉴는 존재하지 않습니다"));
    }

    public List<Menu> findByStoreId(Long storeId) {
        return menuRepository.findByStoreId(storeId);
    }
}
