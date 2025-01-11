package com.example.outsourcing.common.aspect;

import com.example.outsourcing.common.annotation.RequireRole;
import com.example.outsourcing.common.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class RoleCheckAspect {

    private final HttpServletRequest httpRequest;

    @Before("@annotation(requireRole)")
    public void checkBefore(RequireRole requireRole) {

        String requiredRole = requireRole.value();

        String userRole = (String) httpRequest.getAttribute("userRole");

        if (userRole == null || !userRole.equalsIgnoreCase(requiredRole)) {
            throw new UnauthorizedException("권한이 없습니다.");
        }
    }

}
