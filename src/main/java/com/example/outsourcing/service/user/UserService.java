package com.example.outsourcing.service.user;

import com.example.outsourcing.config.PasswordEncoder;
import com.example.outsourcing.dto.user.request.UserChangePasswordRequest;
import com.example.outsourcing.dto.user.request.UserChangeProfileRequest;
import com.example.outsourcing.dto.user.request.UserDeleteRequest;
import com.example.outsourcing.dto.user.response.UserResponse;
import com.example.outsourcing.entity.User;
import com.example.outsourcing.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse findById(Long userId) {
        User user = getUserById(userId);

        if (user.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"탈퇴한 회원입니다.");
        }

        return new UserResponse(user.getId(), user.getEmail(), user.getName());
    }

    public UserResponse updateProfile(Long userId, UserChangeProfileRequest request) {
        User user = getUserById(userId);

        if (request.getName() != null) {
            user.updateName(request.getName());
        }

        if (request.getAddress() != null) {
            user.updateAddress(request.getAddress());
        }

        return new UserResponse(user.getId(), user.getEmail(), user.getName());
    }

    public void updatePassword(Long userId, UserChangePasswordRequest request) {
        User user = getUserById(userId);

        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "새 비밀번호는 기존 비밀번호와 같을 수 없습니다.");
        }

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 비밀번호입니다.");
        }

        user.updatePassword(request.getNewPassword());
    }

    public void deleteUser(Long userId, UserDeleteRequest request) {
        User user = getUserById(userId);

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }

        if (user.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 탈퇴한 회원입니다.");
        }

        user.delete();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
    }
}
