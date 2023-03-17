package com.eazybytes.eazyschool.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    // This method will run the same execution for each method and class in the specified package
    @Around( "execution(* com.eazybytes.eazyschool..*.*(..))" )
    public Object log( ProceedingJoinPoint joinPoint ) throws Throwable {
        log.info( joinPoint.getSignature().toString() + " method execution start" );
        Instant start = Instant.now();
        //Proceed method will return the object returned by a certain invoked method
        Object returnObj = joinPoint.proceed();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between( start, finish ).toMillis();
        log.info( "Time took to execute " + joinPoint.getSignature().toString() + " method is : " + timeElapsed );
        log.info( joinPoint.getSignature().toString() + " method execution end" );
        return returnObj;
    }

    // Will be invoked if the executed method throws an exception
    @AfterThrowing( value = "execution(* com.eazybytes.eazyschool.*.*(..))", throwing = "ex" )
    public void logException( JoinPoint joinPoint, Exception ex ) {
        log.error( joinPoint.getSignature() + " An exception happened due to : " + ex.getMessage() );
    }
}
