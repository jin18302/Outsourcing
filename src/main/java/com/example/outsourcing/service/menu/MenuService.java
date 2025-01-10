package com.example.outsourcing.service.menu;

import com.example.outsourcing.common.exception.InvalidRequestException;
import com.example.outsourcing.common.exception.UnauthorizedException;
import com.example.outsourcing.dto.menu.request.AddMenuRequest;
import com.example.outsourcing.dto.menu.request.UpdateMenuRequest;
import com.example.outsourcing.dto.menu.response.MenuResponse;
import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.service.store.StoreConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService {

    private final MenuConnectorInterface menuConnectorInterface;
    private final StoreConnectorInterface storeConnectorInterface;

    @Transactional
    public MenuResponse saveMenu(Long userId, AddMenuRequest addMenuRequest) {

        Store store = storeConnectorInterface.findById(addMenuRequest.storeId());

        Long storeOwnerId = store.getUser().getId();

        checkPermission(userId, storeOwnerId);

        Menu menu = Menu.from(store, addMenuRequest.name(), addMenuRequest.price());

        Menu saveMenu = menuConnectorInterface.save(menu);

        return MenuResponse.from(saveMenu.getId(), saveMenu.getName(), saveMenu.getPrice());

    }

    @Transactional
    public MenuResponse updateMenu(Long userId, UpdateMenuRequest request) {

        Menu menu = menuConnectorInterface.findById(request.menuId());

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
        List<Menu> menuList = menuConnectorInterface.findByStoreId(storeId);

        return menuList.stream().
                map(menu -> new MenuResponse(menu.getId(), menu.getName(), menu.getPrice())).toList();
    }


    @Transactional
    public void deleteMenu(Long menuId) {
        Menu menu = menuConnectorInterface.findById(menuId);

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
