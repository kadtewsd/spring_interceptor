package com.example.interceptor.kasakaidinterceptor.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class TraceInterceptor {

    @Before("execution(* com.example.interceptor.kasakaidinterceptor.service.*.*(..))")
    public void invokeBefore(JoinPoint joinPoint) {
        System.out.printf("[AOP at before] called parameters = %s, by %s#%s%n",
                Arrays.toString(joinPoint.getArgs()),
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());
        // JoinPoint#getThisの場合は、拡張されたオブジェクトが返る
    }

    @Around("execution(* com.example.interceptor.kasakaidinterceptor.service.*.*(..))")
    public Object packageAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ret = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("package point cut must be called. Time Taken by {} is {}", joinPoint, timeTaken);
        return ret;
    }

    /**
     * this point cut is called although target annotation is class annotation because class annotation is managed by @within
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@within(com.example.interceptor.kasakaidinterceptor.infrastructure.KasakaidClassTrace)")
    public Object withinClassAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ret = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("class point cut by within must be called. Time Taken by {} is {}", joinPoint, timeTaken);
        return ret;
    }
    /**
     * this point cut is not called because class annotation is not managed by @annotation.
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.example.interceptor.kasakaidinterceptor.infrastructure.KasakaidClassTrace)")
    public Object classAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ret = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("Class point cut may not be called. Time Taken by {} is {}", joinPoint, timeTaken);
        return ret;
    }

    /**
     * called by method annotation
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.example.interceptor.kasakaidinterceptor.infrastructure.KasakaidMethodTrace)")
    public Object methodAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ret = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("Method point cut must be called. Time Taken by {} is {}", joinPoint, timeTaken);
        return ret;
    }

    @After("execution(* com.example.interceptor.kasakaidinterceptor.service.*.*(..))")
    public void invokeAfter(JoinPoint joinPoint) {
        System.out.printf("[AOP at After] called parameters = %s, by %s#%s%n",
                Arrays.toString(joinPoint.getArgs()),
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());
        // JoinPoint#getThisの場合は、拡張されたオブジェクトが返る
    }
}
