package com.weiweb.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.weiweb.common.utils.OperType;


/**
 * 操作日志注解定义
 * @author hudie
 *
 */
@Target({ElementType.METHOD})    //用来指定注解修饰类的哪个成员，此处是方法级，还有字段，类
@Retention(RetentionPolicy.RUNTIME)   
//这种类型的Annotations将被JVM保留,所以他们能在运行时被JVM或其他使用反射机制的代码所读取和使用  还有SOURCE CLASS
@Documented //注解表明这个注解应该被 javadoc工具记录
public @interface UserLogAnno {

	/**
	 * 操作类型，常量类com.citms.dop.core.util.Constans  中OPER_TYPE开头的
	 * @return
	 */
	OperType operType();
	/**
	 * 操作描述
	 * @return
	 */
	String operDesc();
	/**
	 * 操作模块名
	 * @return
	 */
	String model();
	
}
