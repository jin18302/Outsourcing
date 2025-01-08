package com.example.outsourcing.controller.purchases;

import com.example.outsourcing.service.purchases.PurchasesService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PurchasesController {

    private final PurchasesService purchasesService;


    public void createOrder(){}
}
