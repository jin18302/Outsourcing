package com.example.outsourcing.controller.store;

import com.example.outsourcing.common.annotation.RequireRole;
import com.example.outsourcing.dto.store.request.StoreRequest;
import com.example.outsourcing.dto.store.request.StoreUpdateRequest;
import com.example.outsourcing.dto.store.response.StoreListResponse;
import com.example.outsourcing.dto.store.response.StoreResponse;
import com.example.outsourcing.dto.store.response.StoreSaveResponse;
import com.example.outsourcing.service.store.StoreService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;

    @RequireRole(value = "OWNER")
    @PostMapping
    public ResponseEntity<StoreSaveResponse> create(
            HttpServletRequest httpRequest,
            @RequestBody StoreRequest request
    ) {
        Long userId = (Long)httpRequest.getAttribute("userId");

        return ResponseEntity.status(HttpStatus.CREATED).body(storeService.create(userId, request));
    }

    @GetMapping("/{store_id}")
    public ResponseEntity<StoreResponse> findById(@PathVariable Long store_id) {

        return ResponseEntity.ok(storeService.findById(store_id));
    }

    @GetMapping
    public ResponseEntity<Page<StoreListResponse>> findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(storeService.findAll(page, size));
    }

    @GetMapping
    public ResponseEntity<Page<StoreListResponse>> findByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(storeService.findByName(name, page, size));
    }

    @GetMapping("/mystores")
    public ResponseEntity<List<StoreListResponse>> findMyStoreList(
            HttpServletRequest httpRequest
    ) {
        Long userId = (Long)httpRequest.getAttribute("userId");

        return ResponseEntity.ok(storeService.findMyStore(userId));
    }

    @PatchMapping("/{storeId}")
    public ResponseEntity<Void> updateStore(
            HttpServletRequest httpRequest,
            @PathVariable Long storeId,
            @RequestBody StoreUpdateRequest request
    ) {
        Long userId = (Long)httpRequest.getAttribute("userId");
        storeService.updateStore(userId, storeId, request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> deleteStore(
            HttpServletRequest httpRequest,
            @PathVariable Long storeId
    ) {
        Long userId = (Long)httpRequest.getAttribute("userId");
        storeService.deleteStore(userId, storeId);

        return ResponseEntity.ok().build();
    }
}