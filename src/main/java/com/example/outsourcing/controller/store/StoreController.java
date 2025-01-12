package com.example.outsourcing.controller.store;

import com.example.outsourcing.common.annotation.RequireRole;
import com.example.outsourcing.dto.store.request.StoreRequest;
import com.example.outsourcing.dto.store.request.StoreUpdateRequest;
import com.example.outsourcing.dto.store.response.StoreListResponse;
import com.example.outsourcing.dto.store.response.StoreResponse;
import com.example.outsourcing.service.store.StoreService;
import jakarta.validation.Valid;
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
    public ResponseEntity<StoreResponse> create(
            @RequestAttribute("userId") Long userId,
            @Valid @RequestBody StoreRequest request
    ) {
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

    @GetMapping("/all")
    public ResponseEntity<Page<StoreListResponse>> findByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(storeService.findByName(name, page, size));
    }

    @RequireRole("OWNER")
    @GetMapping("/mystores")
    public ResponseEntity<List<StoreListResponse>> findMyStoreList(
            @RequestAttribute("userId") Long userId
    ) {
        return ResponseEntity.ok(storeService.findMyStore(userId));
    }

    @RequireRole("OWNER")
    @PatchMapping("/{storeId}")
    public ResponseEntity<StoreResponse> updateStore(
            @RequestAttribute("userId") Long userId,
            @PathVariable Long storeId,
            @Valid @RequestBody StoreUpdateRequest request
    ) {

        return ResponseEntity.ok(storeService.updateStore(userId, storeId, request));
    }

    @RequireRole("OWNER")
    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> deleteStore(
            @RequestAttribute("userId") Long userId,
            @PathVariable Long storeId
    ) {
        storeService.deleteStore(userId, storeId);

        return ResponseEntity.ok().build();
    }
}
