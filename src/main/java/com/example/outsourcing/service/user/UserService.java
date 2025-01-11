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
import com.example.outsourcing.service.store.StoreConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserConnectorInterface userConnectorInterface;
    private final StoreConnectorInterface StoreConnectorInterface;
    private final PasswordEncoder passwordEncoder;

    public UserResponse findById(Long userId) {
        User user = userConnectorInterface.findById(userId);

        return UserResponse.from(user);
    }

    @Transactional
    public UserResponse updateProfile(Long userId, UserChangeProfileRequest request) {
        User user = userConnectorInterface.findById(userId);

        user.updateProfile(request.getName(), request.getAddress());

        User savedUser = userConnectorInterface.save(user);
        return UserResponse.from(savedUser);
    }

    @Transactional
    public void updatePassword(Long userId, UserChangePasswordRequest request) {
        User user = userConnectorInterface.findById(userId);

        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new InvalidRequestException("새 비밀번호는 기존 비밀번호와 같을 수 없습니다.");
        }

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new InvalidRequestException("비밀번호가 일치하지 않습니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getNewPassword());
        user.updatePassword(encodedPassword);
    }

    @Transactional
    public void deleteUser(Long userId, String password) {
        User user = userConnectorInterface.findById(userId);

        if(!passwordEncoder.matches(password,user.getPassword())) {
            throw new InvalidRequestException("비밀번호가 일치하지 않습니다.");
        }

        List<Store> myStores = StoreConnectorInterface.findMyStoresByUserId(userId);
        user.delete();
        myStores.forEach(Store::delete);
    }

}
