package com.example.outsourcing.config.Order;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

@Aspect
public class OrderAspect {

    private Logger logger = Logger.getLogger(OrderAspect.class.getName());

    @Around("execution(* com.example.outsourcing.service.purchases.*( ..))")
    public void orderInfoLog(ProceedingJoinPoint joinPoint)throws Throwable{

        logger.info(LocalTime.now().toString());

        joinPoint.proceed();

        logger.info();
    }
}
