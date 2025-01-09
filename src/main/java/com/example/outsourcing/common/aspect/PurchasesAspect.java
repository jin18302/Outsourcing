package com.example.outsourcing.common.aspect;

import com.example.outsourcing.dto.purchases.response.PurchasesResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;

@Aspect
@Slf4j
public class PurchasesAspect {

    @Pointcut("@annotation(com.example.outsourcing.common.annotation.PurchasesLog)")
    public void serviceLog() {
    }


    @Around("serviceLog()")
    public void orderInfoLog(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            Object result = joinPoint.proceed();

            PurchasesResponse response = (PurchasesResponse) result;

            Long storeId = response.getStoreId();
            Long purchasesId = response.getPurchasesId();

            log.info("요청시각: " + LocalTime.now() + ", storeId: " + storeId + ",  purchasesId: " + purchasesId);

        } catch (Throwable ex) {
            log.info("주문 처리중 오류가 발생하였습니다. 다시 시도해주세요");//
        }
    }
}
