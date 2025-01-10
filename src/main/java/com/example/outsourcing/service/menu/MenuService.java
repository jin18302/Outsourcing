package com.example.outsourcing.service.menu;

import com.example.outsourcing.dto.menu.request.AddMenuRequest;
import com.example.outsourcing.dto.menu.request.UpdateMenuRequest;
import com.example.outsourcing.dto.menu.response.MenuResponse;
import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.entity.Store;
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


    private final MenuRepositoryConnectService menuConnectService;
    private final StoreRepository storeRepository;


    public MenuResponse saveMenu(Long userId, AddMenuRequest addMenuRequest) {

        Store store = storeRepository.findById(addMenuRequest.storeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당가게는 존재하지 않습니다"));

        Long storeOwnerId = store.getUser().getId();

        checkPermission(userId, storeOwnerId);

        Menu menu = new Menu(store, addMenuRequest.name(), addMenuRequest.price());

        Menu saveMenu = menuConnectService.saveMenu(menu);

        return MenuResponse.from(saveMenu);
    }


    public MenuResponse updateMenu(Long userId, UpdateMenuRequest request) {

        Menu menu = menuConnectService.findMenuById(request.menuId());

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
        List<Menu> menuList = menuConnectService.findByStoreId(storeId);

        return menuList.stream().
                map(menu -> new MenuResponse(menu.getId(), menu.getName(), menu.getPrice())).toList();
    }


    public void deleteMenu(Long menuId) {
        Menu menu = menuConnectService.findMenuById(menuId);

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
