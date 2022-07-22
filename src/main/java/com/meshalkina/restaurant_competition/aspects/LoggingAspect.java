package com.meshalkina.restaurant_competition.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("com.meshalkina.restaurant_competition.aspects.MyPointcuts.allCreateMethodsFromService()")
    public Object aroundCreateLoggingAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            log.error("IN {}: Exception raised {}", joinPoint.getSignature().getName(), e.getClass().getSimpleName());
            throw e;
        }
        log.info("IN {}: created new {}", joinPoint.getSignature().getName(), result.getClass().getSimpleName());
        return result;
    }

    @AfterReturning(value = "com.meshalkina.restaurant_competition.aspects.MyPointcuts.allGetAllMethodsFromService()",
            returning = "retVal")
    public void afterReturningGetAllLoggingAdvice(JoinPoint joinPoint, List<?> retVal) throws Throwable {
        try {
            if (retVal.size() > 0) {
                log.info("IN {}: {} objects of the {} class were found", joinPoint.getSignature().getName(),
                        retVal.size(), retVal.get(0).getClass().getSimpleName());
            } else {
                log.error("IN {}: nothing found", joinPoint.getSignature().getName());
            }
        } catch (Exception e) {
            log.error("IN {}: Exception raised {}", joinPoint.getSignature().getName(), e.getClass().getSimpleName());
            throw e;
        }
    }

    @AfterReturning(value = "com.meshalkina.restaurant_competition.aspects.MyPointcuts.allGetByIdMethodsFromService()",
            returning = "retVal")
    public void afterReturningGetByIdLoggingAdvice(JoinPoint joinPoint, Object retVal) throws Throwable {
        try {
            if (retVal != null) {
                log.info("IN {}: object of the {} class was found", joinPoint.getSignature().getName(), retVal.getClass().getSimpleName());
            } else {
                log.error("IN {}: nothing found", joinPoint.getSignature().getName());
            }
        } catch (Exception e) {
            log.error("IN {}: Exception raised {}", joinPoint.getSignature().getName(), e.getClass().getSimpleName());
            throw e;
        }
    }

    @AfterReturning(value = "com.meshalkina.restaurant_competition.aspects.MyPointcuts.allUpdateMethodsFromService()",
            returning = "retVal")
    public void afterReturningUpdateLoggingAdvice(JoinPoint joinPoint, Object retVal) throws Throwable {
        try {
            if (retVal != null) {
                log.info("IN {}: the {} has been successfully updated", joinPoint.getSignature().getName(), retVal.getClass().getSimpleName());
            }
        } catch (Exception e) {
            log.error("IN {}: Exception raised {}", joinPoint.getSignature().getName(), e.getClass().getSimpleName());
            throw e;
        }
    }

    @Around("com.meshalkina.restaurant_competition.aspects.MyPointcuts.allDeleteMethodsFromService()")
    public void aroundDeleteLoggingAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            joinPoint.proceed();
            log.info("IN {}: the object was successfully deleted", joinPoint.getSignature().getName());
        } catch (Throwable e) {
            log.error("IN {}: Exception raised {}", joinPoint.getSignature().getName(), e.getClass().getSimpleName());
            throw e;
        }
    }
}
