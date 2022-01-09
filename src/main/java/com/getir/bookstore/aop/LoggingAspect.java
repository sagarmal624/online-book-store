package com.getir.bookstore.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Aspect
@Component
public class LoggingAspect {
@Autowired
HttpServletRequest httpServletRequest;
    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.stereotype.Component *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(com.getir.bookstore.repository..*)"+
            " || within(com.getir.bookstore.service..*)"+
            " || within(com.getir.bookstore.dto..*)"+
            " || within(com.getir.bookstore.domain..*)"+
            " || within(com.getir.bookstore.exception..*)"+
            " || within(com.getir.bookstore.controller..*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Retrieves the {@link Logger} associated to the given {@link JoinPoint}.
     *
     * @param joinPoint join point we want the logger for.
     * @return {@link Logger} associated to the given {@link JoinPoint}.
     */
    private Logger logger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
    }


    /**
     * Advice that logs when a method is entered and exited.
     *
     * @param joinPoint join point for advice.
     * @return result.
     * @throws Throwable throws {@link IllegalArgumentException}.
     */
    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = logger(joinPoint);
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        log.info("Enter: {}() with argument[s] name = {} and value = {} ", signature.getName(), Arrays.toString(signature.getParameterNames()),Arrays.toString(joinPoint.getArgs()));
        try {
            long startTime = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long endtime = System.currentTimeMillis();
            log.info("Exit: {}() with argument[s] name = {} and value = {} Time taken for Execution is : {}", signature.getName(), Arrays.toString(signature.getParameterNames()),Arrays.toString(joinPoint.getArgs()), (endtime-startTime) +"ms");
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}()", Arrays.toString(joinPoint.getArgs()), signature.getName());
            throw e;
        }
    }
}
