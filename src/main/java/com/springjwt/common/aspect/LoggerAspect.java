package com.springjwt.common.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Aspect
@Configuration
@Slf4j
public class LoggerAspect {

    @Autowired
    private ObjectMapper objectMapper;

    private static final String ERROR_RESPONSE = "Error Response!!!";

    @Before(value = "execution(* com.springjwt.controllers..*.*.*(..))")
    public void beforeAdviceController(JoinPoint joinPoint) {
        log.info("Getting Controller Method : {}", joinPoint.getSignature().getName());
    }

    @Before(value = "execution(* com.springjwt.services..*..*.*.*(..))")
    public void beforeServiceMethod(JoinPoint joinPoint) {
        log.info("Before executing Service method: {}", joinPoint.getSignature().getName());
    }

    @After(value = "execution(* com.springjwt.services.authentication..*..*.*.*(..))")
    public void afterAuthServiceMethod(JoinPoint joinPoint) {
        log.info("After executing Service method: {}", joinPoint.getSignature().getName());
    }

    @After("execution(* com.springjwt.services.authentication..*..*.*.*(..)) && args(url, method, body, returnType,more)")
    public void afterRequestValueAuthServiceMethod(String url, Object method, Object body, Object returnType, Object[] more) {
        log.info("After executing Service method. Request URL: {}", url);
        log.info("After executing Service method. Request Date: {}", body);
    }

    @AfterReturning(value = "execution(* com.springjwt.services.authentication..*..*.*.*(..))", returning = "returnValue")
    public void afterReturnValueAuthServiceMethod(Object returnValue) {
        log.info("After executing Service method. Returned: {}", returnValue);
    }

    @AfterThrowing(value = "execution(* com.springjwt.services.authentication..*..*.*.*(..))", throwing = "ex")
    public void afterThrowingExceptionError(Exception ex) throws IOException {
        log.error("ERROR_RESPONSE: {}", objectMapper.readValue(ex.getMessage(), Object.class));
    }



}
