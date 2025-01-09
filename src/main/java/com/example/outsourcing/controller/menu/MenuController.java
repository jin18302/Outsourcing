package com.example.outsourcing.controller.menu;

import com.example.outsourcing.dto.menu.request.AddMenuRequest;
import com.example.outsourcing.dto.menu.request.UpdateMenuRequest;
import com.example.outsourcing.dto.menu.response.MenuResponse;
import com.example.outsourcing.service.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/menus")
    public ResponseEntity<Void> saveMenu(@RequestAttribute("userId") Long userId, AddMenuRequest addMenuRequest){
        menuService.saveMenu(userId, addMenuRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/menus")
    public ResponseEntity<Void> updateMenu(@RequestAttribute("userId") Long userId,
                                           UpdateMenuRequest updateMenuRequest){

        menuService.updateMenu(userId, updateMenuRequest);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/stores/{storeId}")
    public ResponseEntity <List<MenuResponse>> geeMenuList(@PathVariable(name = "storeId")Long storeId){

       List<MenuResponse> menuResponseList = menuService.getMenus(storeId);

       return  ResponseEntity.status(HttpStatus.OK).body(menuResponseList);
    }


    @DeleteMapping(("/menus/{menuId}"))
    public ResponseEntity<Void> deleteMenu(@PathVariable(name ="menuId")Long menuId){
        menuService.deleteMenu(menuId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
