package com.example.outsourcing.service.menu;

import com.example.outsourcing.common.exception.InvalidRequestException;
import com.example.outsourcing.common.exception.UnauthorizedException;
import com.example.outsourcing.dto.menu.request.AddMenuRequest;
import com.example.outsourcing.dto.menu.request.UpdateMenuRequest;
import com.example.outsourcing.dto.menu.response.MenuResponse;
import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.repository.menu.MenuConnector;
import com.example.outsourcing.repository.store.StoreConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService {

    private final MenuConnector menuConnector;
    private final StoreConnector storeConnector;

    @Transactional
    public MenuResponse saveMenu(Long userId, AddMenuRequest addMenuRequest) {

        Store store = storeConnector.findById(addMenuRequest.storeId());

        Long storeOwnerId = store.getUser().getId();

        checkPermission(userId, storeOwnerId);

        Menu menu = Menu.from(store, addMenuRequest.name(), addMenuRequest.price());

        Menu saveMenu = menuConnector.save(menu);

        return MenuResponse.from(saveMenu.getId(), saveMenu.getName(), saveMenu.getPrice());

    }

    @Transactional
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

        return MenuResponse.from(menu.getId(), menu.getName(), menu.getPrice());

    }


    public List<MenuResponse> getMenus(Long storeId) {
        List<Menu> menuList = menuConnector.findByStoreId(storeId);

        return menuList.stream().
                map(menu -> new MenuResponse(menu.getId(), menu.getName(), menu.getPrice())).toList();
    }


    @Transactional
    public void deleteMenu(Long menuId) {
        Menu menu = menuConnector.findById(menuId);

        if (menu.isDelete()) {
            throw new InvalidRequestException("이미 삭제된 메뉴입니다");
        }

        menu.delete();
    }


    public void checkPermission(Long userId, Long storeOwnerId) {
        if (!userId.equals(storeOwnerId)) {
            throw new UnauthorizedException("권한이 없습니다");
        }
    }


}
