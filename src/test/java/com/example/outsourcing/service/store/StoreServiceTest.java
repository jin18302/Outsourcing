package com.example.outsourcing.service.store;


import com.example.outsourcing.common.exception.InvalidRequestException;
import com.example.outsourcing.common.exception.NotFoundException;
import com.example.outsourcing.dto.auth.request.SignupRequest;
import com.example.outsourcing.dto.store.request.StoreRequest;
import com.example.outsourcing.dto.store.response.StoreResponse;
import com.example.outsourcing.dto.store.response.StoreSaveResponse;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.entity.User;
import com.example.outsourcing.service.user.UserConnectorInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {

    @InjectMocks
    private StoreService storeService;

    @Mock
    private StoreConnectorInterface storeConnectorInterface;

    @Mock
    private UserConnectorInterface userConnectorInterface;

    @Test
    void 가게_생성_잘되니() {
        // given
        Long userId = 1L;
        Long storeId = 1L;
        User user = new User();
        ReflectionTestUtils.setField(user, "id", userId);
        StoreRequest request = StoreRequest.from("가게명", "주소", "09:00", "21:00", 5000);
        Store store = Store.from(
                user,
                request.getName(),
                request.getAddress(),
                request.getOpen(),
                request.getClose(),
                request.getMinAmount());
        Store savedStore = Store.from(
                user,
                request.getName(),
                request.getAddress(),
                request.getOpen(),
                request.getClose(),
                request.getMinAmount());

        ReflectionTestUtils.setField(store, "id", storeId);
        ReflectionTestUtils.setField(savedStore, "id", storeId);

        // when
        Mockito.when(storeConnectorInterface.countByUserId(userId)).thenReturn(0);
        Mockito.when(storeConnectorInterface.save(Mockito.any(Store.class))).thenReturn(savedStore);

        StoreSaveResponse response = storeService.create(userId, request);

        // then
        assertEquals(savedStore.getId(), response.getId());
        assertEquals(savedStore.getName(), response.getName());
    }

    @Test
    void 가게_3개일때_생성_요청() {
        // given
        Long userId = 1L;
        StoreRequest request = StoreRequest.from("가게명", "주소","09:00", "21:00" , 10000);

        // when
        Mockito.when(storeConnectorInterface.countByUserId(userId)).thenReturn(3);

        // then
        Assertions.assertThrows(InvalidRequestException.class, () -> storeService.create(userId, request));
    }

    @Test
    void 가게_ID_검색_성공() {
        // given
        Long storeId = 1L;
        User user = new User();
        Store store = Store.from(user, "가게명", "주소", "09:00", "21:00", 5000);
        ReflectionTestUtils.setField(store, "id", storeId);

        // when
        Mockito.when(storeConnectorInterface.findById(storeId)).thenReturn(store);
        StoreResponse response = storeService.findById(storeId);

        // then
        assertEquals(store.getId(), response.getId());
        assertEquals(store.getName(), response.getName());
    }

    @Test
    void 가게_ID_검색_실패() {
        // given
        Long storeId = 1L;

        // when
        Mockito.when(storeConnectorInterface.findById(storeId)).thenThrow(new NotFoundException("가게없음"));

        // then
        Assertions.assertThrows(NotFoundException.class, () -> storeService.findById(storeId));
    }


    @Test
    void 가게_내꺼_지우기() {
        // given
        Long userId = 1L;
        Long storeId = 1L;

        SignupRequest request = SignupRequest.from("홍길동", "12341234", "용산구", "123@123.123", "OWNER");
        User user = User.from(request);
        Store store = Store.from(user, "가게명", "주소", "09:00", "21:00", 5000);

        ReflectionTestUtils.setField(store, "id", storeId);
        ReflectionTestUtils.setField(user, "id", userId);

        // when
        Mockito.when(storeConnectorInterface.findById(storeId)).thenReturn(store);


        Store result = storeConnectorInterface.findById(storeId);

        // then
        assertEquals(store, result);
        Mockito.verify(storeConnectorInterface, Mockito.times(1)).findById(storeId);
    }
}
