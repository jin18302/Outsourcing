package com.example.outsourcing.controller.purchases;

import com.example.outsourcing.dto.purchases.request.AddPurchasesRequest;
import com.example.outsourcing.dto.purchases.request.UpdatePurchasesRequest;
import com.example.outsourcing.dto.purchases.response.PurchasesResponse;
import com.example.outsourcing.service.purchases.PurchasesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchasesController {

    private final PurchasesService purchasesService;


    @PostMapping
    public ResponseEntity<PurchasesResponse> createPurchases(AddPurchasesRequest request) {
        PurchasesResponse purchases = purchasesService.createPurchases(request);
        return ResponseEntity.status(HttpStatus.OK).body(purchases);
    }


    @PatchMapping("/{purchasesId}")
    public ResponseEntity<PurchasesResponse> cancelPurchases(@RequestAttribute("userId") Long userId,
                                @PathVariable(name = "purchasesId") Long purchasesId) {

        PurchasesResponse purchases = purchasesService.cancelPurchasesByUsers(userId, purchasesId);

        return ResponseEntity.status(HttpStatus.OK).body(purchases);

    }



    @PatchMapping("/{purchasesId}/accept")
    public ResponseEntity<PurchasesResponse> changePurchasesByOwner(@RequestAttribute("userId") Long userId,
                               UpdatePurchasesRequest request) {

        PurchasesResponse purchases =  purchasesService.changePurchasesByOwner(userId, request);

        return ResponseEntity.status(HttpStatus.OK).body(purchases);
    }
}
