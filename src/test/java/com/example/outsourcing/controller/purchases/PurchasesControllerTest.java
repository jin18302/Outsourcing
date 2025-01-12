package com.example.outsourcing.controller.purchases;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc(addFilters = false)
class PurchasesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void 주문_취소() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/purchases/{purchasesId}", 1L)
                .requestAttr("userId",1L))
                .andExpect(jsonPath("$.totalPrice").value(10000));
    }

}