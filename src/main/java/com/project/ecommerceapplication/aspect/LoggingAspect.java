package com.project.ecommerceapplication.aspect;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

    @Around("execution(* com.project.ecommerceapplication..*(..))")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

//        System.out.println(joinPoint.getSignature() + " started at " + startTime + "ms");
//        LOGGER.info(joinPoint.getSignature() + " started at " + startTime + "ms");

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

//        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
//        LOGGER.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");

        return result;
    }
}

