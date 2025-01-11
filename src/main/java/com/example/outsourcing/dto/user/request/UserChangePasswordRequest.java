package com.example.outsourcing.dto.user.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserChangePasswordRequest{
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$",
            message = "비밀번호는 최소 8자 이상이며, 대소문자, 숫자, 특수문자를 각각 최소 1자 이상 포함해야 합니다.")
    private final String oldPassword;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$",
            message = "비밀번호는 최소 8자 이상이며, 대소문자, 숫자, 특수문자를 각각 최소 1자 이상 포함해야 합니다.")
    private final String newPassword;

    private UserChangePasswordRequest(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    @JsonCreator
    public static UserChangePasswordRequest from(
            @JsonProperty("oldPassword")
            String oldPassword,
            @JsonProperty("newPassword")
            String newPassword
    ) {
        return new UserChangePasswordRequest(
                oldPassword,
                newPassword
        );
    }
}
