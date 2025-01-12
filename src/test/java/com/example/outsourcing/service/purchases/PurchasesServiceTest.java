package com.example.outsourcing.service.purchases;

import com.example.outsourcing.common.status.PurchasesStatus;
import com.example.outsourcing.common.status.UserRole;
import com.example.outsourcing.dto.menu.request.AddMenuRequest;
import com.example.outsourcing.dto.purchases.request.AddPurchasesRequest;
import com.example.outsourcing.dto.purchases.response.PurchasesResponse;
import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.entity.Purchases;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.entity.User;
import com.example.outsourcing.repository.purchases.PurchasesRepository;
import com.example.outsourcing.service.menu.MenuConnectorInterface;
import com.example.outsourcing.service.store.StoreConnectorInterface;
import com.example.outsourcing.service.user.UserConnectorInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchasesServiceTest {

    @InjectMocks
    PurchasesService purchasesService;

    @Mock
    PurchasesConnectorInterface purchasesConnectorInterface;

    @Mock
    MenuConnectorInterface menuConnectorInterface;

    @Mock
    StoreConnectorInterface storeConnectorInterface;

    @Mock
    UserConnectorInterface userConnectorInterface;


    @Test
    void 주문_요청_성공(){
        //given

        User user = new User();
        Long userId = 1L;
        ReflectionTestUtils.setField(user,"id", userId);

        Long storeId = 1L;

        Store store = Store.from(user, "이수진", "가게", "00:00", "23:59", 10000);
        ReflectionTestUtils.setField(store,"id", storeId);

        Long menuId = 1L;
        Menu menu = Menu.from(store, "집밥", 15000L);
        ReflectionTestUtils.setField(menu,"id", menuId);


        AddPurchasesRequest request = AddPurchasesRequest.from(userId, storeId, menuId);


        Long purchasesId = 1L;
        Purchases savePurchases = new Purchases(store, menu, menu.getPrice(), user, PurchasesStatus.주문요청);
        ReflectionTestUtils.setField(savePurchases, "id", purchasesId);

        PurchasesResponse response = PurchasesResponse.from(savePurchases);


        //when
        when(storeConnectorInterface.findById(storeId)).thenReturn(store);
        when(menuConnectorInterface.findById(menuId)).thenReturn(menu);
        when(userConnectorInterface.findById(userId)).thenReturn(user);
        when(purchasesConnectorInterface.save(any(Purchases.class))).thenReturn(savePurchases);

       PurchasesResponse actualResponse = purchasesService.createPurchases(request, userId);

        //then
        assertEquals(response.getTotalPrice(),actualResponse.getTotalPrice());
        assertEquals(response.getPurchasesStatus(), actualResponse.getPurchasesStatus());

    }


    @Test
    void


}