package com.example.outsourcing.config;

import com.example.outsourcing.dto.order.response.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.time.LocalTime;

@Aspect
@Slf4j
public class OrderAspect {

    @Pointcut("execution(* com.example.outsourcing.service.purchases.*( ..))")
    public void serviceLog() {
    }


    @Around("serviceLog()")
    public void orderInfoLog(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            Object result = joinPoint.proceed();

            OrderResponse response = (OrderResponse) result;

            Long storeId = response.getStoreId();
            Long menuId = response.getMenuId();

            log.info("요청시각: " + LocalTime.now() + ", storeId: " + storeId + ", menuId: " + menuId);

        } catch (Throwable ex) {
            log.info("주문 처리중 오류가 발생하였습니다. 다시 시도해주세요");
        }
    }
}
