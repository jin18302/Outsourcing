package com.example.outsourcing.service.menu;

import com.example.outsourcing.dto.menu.request.AddMenuRequest;
import com.example.outsourcing.dto.menu.request.UpdateMenuRequest;
import com.example.outsourcing.dto.menu.response.MenuResponse;
import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.repository.menu.MenuConnector;
import com.example.outsourcing.repository.store.StoreConnector;
import com.example.outsourcing.repository.store.StoreRepository;
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

    private final MenuConnector menuConnector;
    private final StoreConnector storeConnector;


    public MenuResponse saveMenu(Long userId, AddMenuRequest addMenuRequest) {

        Store store = storeConnector.findById(addMenuRequest.storeId());

        Long storeOwnerId = store.getUser().getId();

        checkPermission(userId, storeOwnerId);

        Menu menu = Menu.from(store, addMenuRequest.name(), addMenuRequest.price());

        Menu saveMenu = menuConnector.save(menu);

        return MenuResponse.from(saveMenu);

    }


    public MenuResponse updateMenu(Long userId, UpdateMenuRequest request) {

        Menu menu = menuConnector.findById(request.menuId());

        Long storeOwnerId = menu.getStore().getUser().getId();

        checkPermission(userId, storeOwnerId);

        if (request.name() != null) {
            menu.updateName(request.name());
        }

        if (request.price() != null) {
            menu.updatePrice(request.price());
        }

        return MenuResponse.from(menu);

    }


    public List<MenuResponse> getMenus(Long storeId) {
        List<Menu> menuList = menuConnector.findByStoreId(storeId);

        return menuList.stream().
                map(MenuResponse::from).toList();
    }


    public void deleteMenu(Long menuId) {
        Menu menu = menuConnector.findById(menuId);

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
