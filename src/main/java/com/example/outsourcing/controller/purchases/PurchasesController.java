package com.example.outsourcing.controller.purchases;

import com.example.outsourcing.common.annotation.RequireRole;
import com.example.outsourcing.dto.purchases.request.AddPurchasesRequest;
import com.example.outsourcing.dto.purchases.request.UpdatePurchasesRequest;
import com.example.outsourcing.dto.purchases.response.PurchasesResponse;
import com.example.outsourcing.service.purchases.PurchasesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchasesController {

    private final PurchasesService purchasesService;


    @PostMapping
    public ResponseEntity<PurchasesResponse> createPurchases(
            @RequestAttribute("userId") Long userId,
            @Valid @RequestBody AddPurchasesRequest request) {
        PurchasesResponse purchases = purchasesService.createPurchases(request, userId);
        return ResponseEntity.status(HttpStatus.OK).body(purchases);
    }

    @RequireRole("OWNER")
    @GetMapping("/stores/{storeId}")
    public ResponseEntity<List<PurchasesResponse>> getStorePurchases(
            @RequestAttribute("userId") Long userId,
            @PathVariable Long storeId
    ) {
        List<PurchasesResponse> purchases = purchasesService.getStorePurchases(userId, storeId);
        return ResponseEntity.status(HttpStatus.OK).body(purchases);
    }

    @GetMapping("/users")
    public ResponseEntity<List<PurchasesResponse>> getUserPurchases(
            @RequestAttribute("userId") Long userId
    ) {
        List<PurchasesResponse> purchases = purchasesService.getUserPurchases(userId);
        return ResponseEntity.status(HttpStatus.OK).body(purchases);
    }


    @DeleteMapping("/{purchasesId}")
    public ResponseEntity<PurchasesResponse> cancelPurchases(
            @RequestAttribute("userId") Long userId,
            @PathVariable(name = "purchasesId") Long purchasesId) {

        PurchasesResponse purchases = purchasesService.cancelPurchasesByUsers(userId, purchasesId);

        return ResponseEntity.status(HttpStatus.OK).body(purchases);

    }

    @RequireRole("OWNER")
    @PatchMapping
    public ResponseEntity<PurchasesResponse> changePurchasesByOwner(
            @RequestAttribute("userId") Long userId,
            @Valid @RequestBody UpdatePurchasesRequest request) {

        PurchasesResponse purchases =  purchasesService.changePurchasesByOwner(userId, request);

        return ResponseEntity.status(HttpStatus.OK).body(purchases);
    }
}
