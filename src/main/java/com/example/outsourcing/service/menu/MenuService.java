package com.example.outsourcing.service.menu;

import com.example.outsourcing.dto.menu.request.AddMenuRequest;
import com.example.outsourcing.dto.menu.request.UpdateMenuRequest;
import com.example.outsourcing.dto.menu.response.MenuResponse;
import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.repository.menu.MenuRepository;
import com.example.outsourcing.repository.store.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당메뉴는 존재하지 않습니다"));

        Long storeOwnerId = menu.getStore().getUser().getId();

        checkPermission(userId, storeOwnerId);

        if (request.getName() != null) {
            menu.updateName(request.getName());
        }

        if (request.getPrice() != null) {
            menu.updatePrice(request.getPrice());
        }

    }


    public List<MenuResponse> getMenus(Long storeId) {
        List<Menu> menuList = menuRepository.findByStoreId(storeId);

        return menuList.stream().
                map(menu -> new MenuResponse(menu.getName(), menu.getPrice())).toList();
    }


    public void deleteMenu(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당메뉴는 존재하지 않습니다"));

        if (menu.isDelete()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 삭제된 메뉴입니다");
        }

        menu.delete();
    }


    public void checkPermission(Long userId, Long storeOwnerId) {
        if (!userId.equals(storeOwnerId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다");
        }
    }


}
