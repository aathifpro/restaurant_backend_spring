package com.springjwt.common.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

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
    public void AfterAuthServiceMethod(JoinPoint joinPoint) {
        log.info("After executing Service method: {}", joinPoint.getSignature().getName());
    }

    @After("execution(* com.springjwt.services.authentication..*..*.*.*(..)) && args(url, method, body, returnType,more)")
    public void AfterRequestValueAuthServiceMethod(String url, Object method, Object body, Object returnType, Object[] more) {
        log.info("After executing Service method. Request URL: {}", url);
        log.info("After executing Service method. Request Date: {}", body);
    }

    @AfterReturning(value = "execution(* com.springjwt.services.authentication..*..*.*.*(..))", returning = "returnValue")
    public void AfterReturnValueAuthServiceMethod(Object returnValue) {
        log.info("After executing Service method. Returned: {}", returnValue);
    }

}
