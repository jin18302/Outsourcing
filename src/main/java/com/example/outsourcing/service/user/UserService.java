package com.example.outsourcing.service.user;

import com.example.outsourcing.common.exception.InvalidRequestException;
import com.example.outsourcing.config.PasswordEncoder;
import com.example.outsourcing.dto.user.request.UserChangePasswordRequest;
import com.example.outsourcing.dto.user.request.UserChangeProfileRequest;
import com.example.outsourcing.dto.user.request.UserDeleteRequest;
import com.example.outsourcing.dto.user.response.UserResponse;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.entity.User;
import com.example.outsourcing.repository.store.StoreRepository;
import com.example.outsourcing.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse findById(Long userId) {
        User user = getUserById(userId);

        return new UserResponse(user.getId(), user.getEmail(), user.getName());
    }

    @Transactional
    public UserResponse updateProfile(Long userId, UserChangeProfileRequest request) {
        User user = getUserById(userId);

        user.updateProfile(request.name(), request.address());

        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getName());
    }

    @Transactional
    public void updatePassword(Long userId, UserChangePasswordRequest request) {
        User user = getUserById(userId);

        if (passwordEncoder.matches(request.newPassword(), user.getPassword())) {
            throw new InvalidRequestException("새 비밀번호는 기존 비밀번호와 같을 수 없습니다.");
        }

        if (!passwordEncoder.matches(request.oldPassword(), user.getPassword())) {
            throw new InvalidRequestException("비밀번호가 일치하지 않습니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.newPassword());
        user.updatePassword(encodedPassword);
    }

    @Transactional
    public void deleteUser(Long userId, UserDeleteRequest request) {
        User user = getUserById(userId);

        if(!passwordEncoder.matches(request.password(),user.getPassword())) {
            throw new InvalidRequestException("비밀번호가 일치하지 않습니다.");
        }

        List<Store> myStores = storeRepository.findMyStoresByUserId(userId);
        user.delete();
        myStores.forEach(Store::delete);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
    }
}
