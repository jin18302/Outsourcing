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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

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

    User user = new User();
    Store store = Store.from(user, "이수진", "가게", "00:00", "23:59", 10000);
    Menu menu = Menu.from(store, "집밥", 15000L);
    Purchases savePurchases = new Purchases(store, menu, menu.getPrice(), user, PurchasesStatus.주문요청);

    Long userId = 1L;
    Long storeId = 1L;
    Long menuId = 1L;
    Long purchasesId = 1L;


    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(menu,"id", menuId);
        ReflectionTestUtils.setField(user,"id", userId);
        ReflectionTestUtils.setField(store,"id", storeId);
        ReflectionTestUtils.setField(savePurchases, "id", purchasesId);
    }

    @Test
    void 주문_요청_성공(){
        //given
        AddPurchasesRequest request = AddPurchasesRequest.from(storeId, menuId, userId);
        PurchasesResponse response = PurchasesResponse.from(savePurchases);

        when(storeConnectorInterface.findById(storeId)).thenReturn(store);
        when(menuConnectorInterface.findById(menuId)).thenReturn(menu);
        when(userConnectorInterface.findById(userId)).thenReturn(user);
        when(purchasesConnectorInterface.save(any(Purchases.class))).thenReturn(savePurchases);

        //when
        PurchasesResponse actualResponse = purchasesService.createPurchases(request, userId);

        //then
        assertEquals(response.getTotalPrice(),actualResponse.getTotalPrice());
        assertEquals(response.getPurchasesStatus(), actualResponse.getPurchasesStatus());

    }


    @Test
    void 가게별_주문내역_확인(){

        PurchasesResponse response = PurchasesResponse.from(savePurchases);

        //given
        List<Purchases> responseList = List.of(savePurchases, savePurchases, savePurchases);

        when(storeConnectorInterface.findById(storeId)).thenReturn(store);
        when(purchasesConnectorInterface.findAllByStoreId(storeId)).thenReturn(responseList);


        //when
        List<PurchasesResponse> actualResponseList = purchasesService.getStorePurchases(userId, storeId);

        //then
        Assertions.assertEquals(actualResponseList.get(0).getTotalPrice(), responseList.get(0).getTotalPrice());
        Assertions.assertEquals(responseList.get(0).getPurchasesStatus().toString(), actualResponseList.get(0).getPurchasesStatus());

        Assertions.assertEquals(actualResponseList.get(1).getTotalPrice(), responseList.get(1).getTotalPrice());
        Assertions.assertEquals(responseList.get(1).getPurchasesStatus().toString(), actualResponseList.get(1).getPurchasesStatus());


        Assertions.assertEquals(actualResponseList.get(2).getTotalPrice(), responseList.get(2).getTotalPrice());
        Assertions.assertEquals(responseList.get(2).getPurchasesStatus().toString(), actualResponseList.get(2).getPurchasesStatus());

    }


}