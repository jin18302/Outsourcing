package com.example.outsourcing.controller.purchases;

import com.example.outsourcing.dto.purchases.request.AddPurchasesRequest;
import com.example.outsourcing.dto.purchases.request.UpdatePurchasesStatusRequest;
import com.example.outsourcing.dto.purchases.response.PurchasesResponse;
import com.example.outsourcing.service.purchases.PurchasesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PurchasesController {

    private final PurchasesService purchasesService;


    @PostMapping("/{purchasesId}")
    public PurchasesResponse createPurchases(AddPurchasesRequest request) {
        PurchasesResponse purchases = purchasesService.createPurchases(request);
        return purchases;
    }


    @PostMapping("/{purchasesId}")
    public PurchasesResponse cancelPurchases(@RequestAttribute("userId") Long userId,
                                @PathVariable(name = "purchasesId") Long purchasesId) {

        PurchasesResponse purchases = purchasesService.cancelPurchasesByUser(userId, purchasesId);

        return purchases;

    }



    @PostMapping
    public PurchasesResponse changePurchasesByOwner(@RequestAttribute("userId") Long userId,
                                UpdatePurchasesStatusRequest request) {

        PurchasesResponse purchases =  purchasesService.changePurchasesByOwner(userId, request);

        return purchases;
    }
}
