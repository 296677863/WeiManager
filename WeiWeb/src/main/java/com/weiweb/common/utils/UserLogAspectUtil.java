package com.weiweb.common.utils;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 操作日志工具类
 * 
 * @author hudie
 *
 */
public class UserLogAspectUtil {

	
	/**
	 * 获得切点的方法
	 * 
	 * @param joinPoint
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("rawtypes")
	public static Method getTargetMethod(JoinPoint joinPoint) throws SecurityException, NoSuchMethodException {
		Object target = joinPoint.getTarget();
		// 拦截的方法参数类型
		Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
		// 拦截的方法
		Method method = target.getClass().getMethod(getMethodName(joinPoint), parameterTypes);
		return method;
	}

	/**
	 * 获得切点的方法名
	 * 
	 * @param joinPoint
	 * @return
	 */
	public static String getMethodName(JoinPoint joinPoint) {
		return joinPoint.getSignature().getName();
	}

	/**
	 * 获得切点的类路径
	 * 
	 * @param joinPoint
	 * @return
	 */
	public static String getActionPath(JoinPoint joinPoint) {
		Object target = joinPoint.getTarget();
		String actionPath = target.getClass().getName();
		return actionPath;
	}
}
