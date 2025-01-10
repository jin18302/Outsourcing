package com.example.outsourcing.common.aspect;

import com.example.outsourcing.dto.purchases.response.PurchasesResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import java.time.LocalTime;


@Slf4j
@Aspect
public class PurchasesAspect {

    @Pointcut("@annotation(com.example.outsourcing.common.annotation.PurchasesLog)")
    public void serviceLog() {
    }


    @Around("serviceLog()")
    public PurchasesResponse orderInfoLog(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            Object result = joinPoint.proceed();

            PurchasesResponse response = (PurchasesResponse) result;

            Long storeId = response.getStoreId();
            Long purchasesId = response.getPurchasesId();

            log.info("요청시각: " + LocalTime.now() + ", storeId: " + storeId + ",  purchasesId: " + purchasesId +
                    "주문상태" + response.getPurchasesStatus());

            return response;

        } catch (Exception ex) {
            log.info("주문 처리중 오류가 발생하였습니다. 다시 시도해주세요");

            throw ex;
        }
    }
}
