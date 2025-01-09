package com.example.outsourcing.common.aspect;

import com.example.outsourcing.common.annotation.RequireRole;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class RoleCheckAspect {

    private final HttpServletRequest httpRequest;

    @Before("@annotation(requireRole)")
    public void checkBefore(JoinPoint joinPoint, RequireRole requireRole) {

        String requiredRole = requireRole.value();

        String userRole = (String) httpRequest.getAttribute("userRole");

        if (userRole == null || !userRole.equalsIgnoreCase(requiredRole)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"권한이 없습니다.");
        }
    }

}
