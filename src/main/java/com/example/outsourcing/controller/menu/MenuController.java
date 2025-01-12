package com.example.outsourcing.controller.menu;

import com.example.outsourcing.common.annotation.RequireRole;
import com.example.outsourcing.dto.menu.request.AddMenuRequest;
import com.example.outsourcing.dto.menu.request.UpdateMenuRequest;
import com.example.outsourcing.dto.menu.response.MenuResponse;
import com.example.outsourcing.service.menu.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @RequireRole("OWNER")
    @PostMapping("/menus")
    public ResponseEntity<MenuResponse> saveMenu(@RequestAttribute("userId") Long userId,
                                                 @Valid @RequestBody AddMenuRequest addMenuRequest){

        MenuResponse menuResponse = menuService.saveMenu(userId, addMenuRequest);

        return ResponseEntity.status(HttpStatus.OK).body(menuResponse);
    }

    @RequireRole("OWNER")
    @PatchMapping("/menus")
    public ResponseEntity<MenuResponse> updateMenu(
            @RequestAttribute("userId") Long userId,
            @Valid @RequestBody UpdateMenuRequest updateMenuRequest){

        MenuResponse menuResponse = menuService.updateMenu(userId, updateMenuRequest);

        return ResponseEntity.status(HttpStatus.OK).body(menuResponse);
    }


    @GetMapping("/menus/stores/{storeId}")
    public ResponseEntity <List<MenuResponse>> getMenuList(@PathVariable(name = "storeId")Long storeId){

       List<MenuResponse> menuResponseList = menuService.getMenus(storeId);

       return  ResponseEntity.status(HttpStatus.OK).body(menuResponseList);
    }


    @RequireRole("OWNER")
    @DeleteMapping(("/menus/{menuId}"))
    public ResponseEntity<Void> deleteMenu(@PathVariable(name ="menuId")Long menuId){
        menuService.deleteMenu(menuId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
