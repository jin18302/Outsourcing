package com.example.outsourcing.controller.purchases;

import com.example.outsourcing.dto.purchases.request.AddPurchasesRequest;
import com.example.outsourcing.dto.purchases.request.UpdatePurchasesStatusRequest;
import com.example.outsourcing.dto.purchases.response.PurchasesResponse;
import com.example.outsourcing.entity.Purchases;
import com.example.outsourcing.service.purchases.PurchasesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class PurchasesController {

    private final PurchasesService purchasesService;


    @PostMapping("/{purchasesId}")
    public PurchasesResponse createPurchases(AddPurchasesRequest request) {
        PurchasesResponse purchases = purchasesService.createPurchases(request);
        return purchases;
    }


    @PostMapping("/{purchasesId}")
    public void cancelPurchases(@RequestAttribute("userId") Long userId,
                                @PathVariable(name = "purchasesId") Long purchasesId) {

        purchasesService.cancelPurchases(userId, purchasesId);

    }

    @PostMapping("/{purchasesId}")
    public void changePurchases(@RequestAttribute("userId") Long userId,
                                @PathVariable(name = "purchasesId") Long purchasesId,
                                UpdatePurchasesStatusRequest request) {

        purchasesService.purchasesStatusChange(userId, purchasesId, request.getPurchasesStatus());

    }
}
