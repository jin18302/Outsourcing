package com.example.outsourcing.controller.menu;

import com.example.outsourcing.dto.menu.response.MenuResponse;
import com.example.outsourcing.service.menuService.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    
    public ResponseEntity<MenuResponse> saveMenu(){
        return null;
    }

    public ResponseEntity<MenuResponse> updateMenu(){
        return null;
    }
    
    public ResponseEntity<List<MenuResponse>> findAll(){
        return null;
    }
    
    public ResponseEntity<MenuResponse> findById(){
        return null;
    }
}
