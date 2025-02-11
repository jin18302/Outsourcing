package com.example.outsourcing.controller.user;

import com.example.outsourcing.dto.user.request.UserChangePasswordRequest;
import com.example.outsourcing.dto.user.request.UserChangeProfileRequest;
import com.example.outsourcing.dto.user.response.UserResponse;
import com.example.outsourcing.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> findUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }


    @PatchMapping("/users")
    public ResponseEntity<UserResponse> updateProfile(
            @RequestAttribute("userId") Long userId,
            @Valid @RequestBody UserChangeProfileRequest request
    ) {
        return ResponseEntity.ok(userService.updateProfile(userId, request));
    }

    @PatchMapping("/users/password")
    public ResponseEntity<UserResponse> updatePassword(
            @RequestAttribute("userId") Long userId,
            @Valid @RequestBody UserChangePasswordRequest request
    ) {

        return ResponseEntity.ok(userService.updatePassword(userId, request));
    }

    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteUser(
            @RequestAttribute("userId") Long userId,
            @RequestParam String password
    ) {
        userService.deleteUser(userId, password);
        return ResponseEntity.ok().build();
    }
}
