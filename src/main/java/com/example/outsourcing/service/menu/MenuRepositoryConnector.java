package com.example.outsourcing.service.menu;

import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.repository.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuRepositoryConnector {

    private final MenuRepository menuRepository;

    public Menu saveMenu(Menu menu){
       return menuRepository.save(menu);
    }

    public Menu findMenuById(Long id){
        return menuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당메뉴는 존재하지 않습니다"));
    }

    public List<Menu> findByStoreId(Long id){
      return  menuRepository.findByStoreId(id);
    }

}
