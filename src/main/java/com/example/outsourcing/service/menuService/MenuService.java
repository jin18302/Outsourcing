package com.example.outsourcing.service.menuService;

import com.example.outsourcing.dto.menu.response.MenuResponse;
import com.example.outsourcing.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;


    public MenuResponse saveMenu() {
        return null;
    }

    public MenuResponse updateMenu() {
        return null;
    }

    public List<MenuResponse> findAllMenu() {
        return null;
    }

    public MenuResponse findById() {
        return null;
    }

    public void deleteMenu(){

    }

}
