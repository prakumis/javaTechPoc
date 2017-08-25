package com.nyp.shopping.common.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * pointcut definition for all classes
	 */
	@Pointcut("within(com.nyp.shopping..*)")
	public void logAll() {
		/**
		 * pointcut definition for all classes
		 */
	}

	/**
	 * pointcut definition for all methods returning void.
	 */
	@Pointcut("execution(public void com.alcs.*..*(..))")
	public void executeVoidMethod() {
		/**
		 * pointcut definition for all methods returning void.
		 */
	}

	/**
	 * pointcut definition to exclude before advice.
	 */
	@Pointcut("@annotation(ExcludeBeforeLogging) || @annotation(ExcludeAOPLogging)")
	public void excludeBeforeLogging() {
		/**
		 * pointcut definition for exclude before advice.
		 */
	}

	/**
	 * pointcut definition to exclude after advice.
	 */
	@Pointcut("@annotation(ExcludeAfterLogging) || @annotation(ExcludeAOPLogging)")
	public void excludeAfterLogging() {
		/**
		 * pointcut definition to exclude after advice.
		 */
	}

	/**
	 * pointcut definition to exclude after returning advice.
	 */
	@Pointcut("@annotation(ExcludeAfterReturningLogging) || @annotation(ExcludeAOPLogging)")
	public void excludeAfterReturningLogging() {
		/**
		 * pointcut definition to exclude after returning advice.
		 */
	}

	/**
	 * pointcut definition for all methods except returning void.
	 */
	@Pointcut("logAll() && !executeVoidMethod() && !excludeAfterReturningLogging()")
	public void logAllAndExcludeVoidMethod() {
		/**
		 * pointcut definition for all methods except returning void.
		 */
	}

	/**
	 * pointcut definition for all methods returning void.
	 */
	@Pointcut("logAll() && executeVoidMethod() && !excludeAfterReturningLogging()")
	public void logAllAndExcludeNonVoidMethod() {
		/**
		 * pointcut definition for all methods except returning void.
		 */
	}

	@Before("logAll() && !excludeBeforeLogging()")
	public void logBefore(JoinPoint joinPoint) {

		logger.info("Entered Method {}",
				joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
	}

	@After("logAllAndExcludeNonVoidMethod()")
	public void logAfter(JoinPoint joinPoint) {

		logger.info("Exited Void Method {}",
				joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
	}

	@AfterReturning(pointcut = "logAllAndExcludeVoidMethod()", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {

		logger.info("Exited Method {}, return value={}",
				joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(), result);
	}

	@AfterThrowing(pointcut = "logAll()", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

		logger.error("Error in Method {}, error value=",
				joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(), error);
	}
}
