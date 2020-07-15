package com.farben.readme.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ControllerLogAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.farben.readme.controller..*(..))")
    public void logPointCut(){
    }

    @Around("(logPointCut())")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        String typeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object args[] = joinPoint.getArgs();
        log.info("{}-{} start : args:{}", typeName, methodName, Arrays.toString(args));
        Object o = joinPoint.proceed();
        String result = o.toString();
        long execTime = System.currentTimeMillis()- start;
        log.info("{}-{} end, exec time : {}, result:{}", typeName, methodName, execTime, result);
        return o;

    }
}
