
package com.weiweb.core.shiro.po;

import java.util.Locale;

import org.springframework.web.servlet.LocaleResolver;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 消息
 */
public class Message {

	/**
	 * 类型
	 */
	public enum Type {

		/** 成功 */
		success,

		/** 警告 */
		warn,

		/** 错误 */
		error,
		/**
		 * session超时
		 */
		sessionTimeOunt
	}

	/** 类型 */
	private Type type;

	/** 内容 */
	private String content;

	/**
	 * 初始化一个新创建的 Message 对象，使其表示一个空消息。
	 */
	public Message() {

	}

	/**
	 * 初始化一个新创建的 Message 对象
	 * 
	 * @param type
	 *            类型
	 * @param content
	 *            内容
	 */
	public Message(Type type, String content) {
		this.type = type;
		this.content = content;
	}

	/**
	 * @param type
	 *            类型
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 */
	public Message(Type type, String content, Object... args) {
		this.type = type;
		this.content = content;
	}

	/**
	 * 返回成功消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 成功消息
	 */
	public static Message successi18n(String content, Object... args) {
		return new Message(Type.success, content, args);
	}
	
	/***
	 * 返回成功消息
	 * @param obj
	 * @return
	 */
	public static Message success(Object obj) {
		if(obj instanceof  String){
			return new Message(Type.success,obj.toString());
		}
		return new Message(Type.success,JSONObject.toJSONString(obj));
	}

	/***
	 * 返回成功消息并且去除空值
	 * @param obj
	 * @return
	 */
	public static Message successNullRemoval(Object obj) {
		return new Message(Type.success,JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue));
	}

	/**
	 * 返回警告消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 警告消息
	 */
	public static Message warn(String content, Object... args) {
		return new Message(Type.warn, content, args);
	}

	/**
	 * 返回错误消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 错误消息
	 */
	public static Message error(String content, Object... args) {
		return new Message(Type.error, content, args);
	}

	/**
	 * 获取类型
	 * 
	 * @return 类型
	 */
	public Type getType() {
		return type;
	}

	/**
	 * 设置类型
	 * 
	 * @param type
	 *            类型
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * 
	 * @param content
	 *            内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return content;
	}
	

}