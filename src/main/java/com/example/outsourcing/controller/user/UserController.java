package com.example.outsourcing.controller.user;

import com.example.outsourcing.dto.user.request.UserChangePasswordRequest;
import com.example.outsourcing.dto.user.request.UserChangeProfileRequest;
import com.example.outsourcing.dto.user.request.UserDeleteRequest;
import com.example.outsourcing.dto.user.response.UserResponse;
import com.example.outsourcing.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
            HttpServletRequest httpRequest,
            @RequestBody UserChangeProfileRequest request
    ) {
        Long userId = (Long)httpRequest.getAttribute("userId");
        return ResponseEntity.ok(userService.updateProfile(userId, request));
    }

    @PatchMapping("/users/password")
    public ResponseEntity<Void> updatePassword(
            HttpServletRequest httpRequest,
            @RequestBody UserChangePasswordRequest request
    ) {
        Long userId = (Long)httpRequest.getAttribute("userId");
        userService.updatePassword(userId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteUser(
            HttpServletRequest httpRequest,
            @RequestBody UserDeleteRequest request
    ) {
        Long userId = (Long)httpRequest.getAttribute("userId");
        userService.deleteUser(userId, request);
        return ResponseEntity.ok().build();
    }
}
