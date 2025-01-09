package com.example.outsourcing.service.menu;

import com.example.outsourcing.dto.menu.request.AddMenuRequest;
import com.example.outsourcing.dto.menu.request.UpdateMenuRequest;
import com.example.outsourcing.dto.menu.response.MenuResponse;
import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.repository.menu.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;


    public void saveMenu(Long userId, AddMenuRequest addMenuRequest) {

        Store store = storeRepository.findById(addMenuRequest.getStoreId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당가게는 존재하지 않습니다"));

        Long storeOwnerId = store.getUser().getId();

        checkPermission(userId, storeOwnerId);

        Menu menu = new Menu(store, addMenuRequest.getName(), addMenuRequest.getPrice());

        menuRepository.save(menu);

    }


    public void updateMenu(Long userId, UpdateMenuRequest request) {
        Menu menu = menuRepository.findById(request.getMenuId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.OK, "해당메뉴는 존재하지 않습니다"));

        Long storeOwnerId = menu.getStore().getUser().getId();

        checkPermission(userId, storeOwnerId);

        menu.update(request.getName(), request.getPrice());

    }

    public List<MenuResponse> getMenus(Long storeId) {
        List<Menu> menuList = menuRepository.findByStoreId(storeId);

        List<MenuResponse> menuResponseList = menuList.stream().
                map(menu -> new MenuResponse(menu.getName(), menu.getPrice())).toList();

        return menuResponseList;
    }

    public void deleteMenu(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당메뉴가 존재하지 않습니다"));

        menu.delete();
    }


    public void checkPermission(Long userId, Long storeOwnerId) {
        if (!userId.equals(storeOwnerId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "권한이 없습니다");
        }
    }
}
