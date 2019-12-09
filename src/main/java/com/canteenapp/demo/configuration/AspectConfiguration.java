package com.canteenapp.demo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@Aspect
@Slf4j
public class AspectConfiguration {

    @Before("execution(* com.canteenapp.demo.service.*.*(..))")
    public void logOperations(JoinPoint joinPoint) {
        log.info("Before executing {}", joinPoint.toString());
    }

    @AfterThrowing("execution(* com.canteenapp.demo.service.*.*(..))")
    public void logExceptions(JoinPoint joinPoint) {
        log.info("Exception thrown in {}", joinPoint.toString());
    }
}
