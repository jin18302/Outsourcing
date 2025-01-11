package com.example.outsourcing.config;

import com.example.outsourcing.common.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InvalidClassException;


@Slf4j
@RequiredArgsConstructor
public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String url = httpRequest.getRequestURI();

        if (url.startsWith("/api/auth")) {
            chain.doFilter(request, response);
            return;
        }

        String bearerJwt = httpRequest.getHeader("Authorization");

        if (bearerJwt == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        String jwt = jwtUtil.substringToken(bearerJwt);

        try {
            Claims claims = jwtUtil.extractClaims(jwt);
            if (claims == null) {
                throw new InvalidClassException("잘못된 JWT 토큰입니다.");
            }

            httpRequest.setAttribute("userId", Long.parseLong(claims.getSubject()));
            httpRequest.setAttribute("email", claims.get("email"));
            httpRequest.setAttribute("userRole", claims.get("userRole"));

            chain.doFilter(request, response);
        } catch (SecurityException | MalformedJwtException e) {
            throw new UnauthorizedException("유효하지 않는 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(null, null, "로그인 해주세요");
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtException("지원되지 않는 JWT 토큰입니다.");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}