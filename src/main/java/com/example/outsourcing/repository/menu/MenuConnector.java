package com.example.outsourcing.repository.menu;

import com.example.outsourcing.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuConnector {
    private final MenuRepository menuRepository;

    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu findById(Long id) {
        return menuRepository.findById(id).orElseThrow(() -> new RuntimeException("던져던져"));
    }

    public List<Menu> findByStoreId(Long storeId) {
        return menuRepository.findByStoreId(storeId);
    }
}
