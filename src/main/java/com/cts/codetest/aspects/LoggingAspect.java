package com.cts.codetest.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
	
	 @Pointcut("execution(* com.cts.codetest.dao.*.*(..))")
     public void daoMethods() { }
		
	 @Pointcut("execution(* com.cts.codetest.controller.*.*(..))")
     public void controllerMethods() { }
		
	 @Pointcut("execution(* com.cts.codetest.service.*.*(..))")
     public void serviceMethods() { }
		
	 public LoggingAspect() {
		 LOGGER.info("LoggingAspect Cunstructor.");	
	 }
	 
	@Around("daoMethods() || controllerMethods() || serviceMethods()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        LOGGER.info("Going to call the method.");
        Object output = pjp.proceed();
        LOGGER.info("Method execution completed.");
        long elapsedTime = System.currentTimeMillis() - start;
        LOGGER.info(pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName() + ":  Method execution time: " + elapsedTime + " milliseconds.");
        return output;
	}
}
