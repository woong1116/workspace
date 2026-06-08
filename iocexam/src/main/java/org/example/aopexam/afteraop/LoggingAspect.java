package org.example.aopexam.afteraop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(1)  // 실행 순서 지정
public class LoggingAspect {

    private static final Logger logger =
            LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* org.example.aopexam..*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Before executing: {} with arguments: {}",
                joinPoint.getSignature().toShortString(),
                Arrays.toString(joinPoint.getArgs())
        );
    }

    @AfterReturning(
            pointcut = "execution(* org.example.aopexam..*.*(..))",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method {} returned: {}",
                joinPoint.getSignature().toShortString(),
                result
        );
    }

    @AfterThrowing(
            pointcut = "execution(* org.example.aopexam..*.*(..))",
            throwing = "exception"
    )
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
        logger.error("Exception in {}: {}",
                joinPoint.getSignature().toShortString(),
                exception.getMessage()
        );
    }
}