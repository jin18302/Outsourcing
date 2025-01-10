package com.example.outsourcing.repository.menu;

import com.example.outsourcing.common.exception.InvalidRequestException;
import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.service.menu.MenuConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuConnector implements MenuConnectorInterface {
    private final MenuRepository menuRepository;

    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu findById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new InvalidRequestException("해당 메뉴는 존재하지 않습니다"));
    }

    public List<Menu> findByStoreId(Long storeId) {
        return menuRepository.findByStoreId(storeId);
    }
}
