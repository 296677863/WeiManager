package com.weiweb.core.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 正常操作日志切面 使用注解的切点
 * @author hudie
 *
 */
@Aspect
@Component
public class UserLogAspect {

	private static final Logger log = LoggerFactory.getLogger(UserLogAspect.class);




	
	/**
	 * 方法正常返回时的监听
	 * @param joinpoint
	 * @param result
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@AfterReturning(pointcut = "@annotation(com.weiweb.core.annotation.UserLogAnno)",returning="result")
	public void doReturning(JoinPoint joinpoint,Object result) throws SecurityException, NoSuchMethodException{

		log.info(" doReturning start ");
	
	}
	
	
	/**
	 * 抛出异常时的监听
	 * @param joinpoint
	 * @param ex
	 */
	@AfterThrowing(pointcut ="execution(* com.weiweb.*.controller.*.*(..))", throwing = "ex")
	public void doThrowing(JoinPoint joinpoint, Throwable ex) {
		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
