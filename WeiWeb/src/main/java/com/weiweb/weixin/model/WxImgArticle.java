package com.weiweb.weixin.model;

import java.io.Serializable;


/**
 * 
 * 
 * @author zed
 * @email 296677863@qq.com
 * @date 2017-09-01
 */

public class WxImgArticle implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String createDate;
	//
	private String modifyDate;
	//
	private String author;
	//
	private String content;
	//
	private String cover;
	//
	private String href;
	//
	private Integer showCover;
	//
	private String summary;
	//
	private String title;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：
	 */
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * 获取：
	 */
	public String getModifyDate() {
		return modifyDate;
	}
	/**
	 * 设置：
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 设置：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：
	 */
	public void setCover(String cover) {
		this.cover = cover;
	}
	/**
	 * 获取：
	 */
	public String getCover() {
		return cover;
	}
	/**
	 * 设置：
	 */
	public void setHref(String href) {
		this.href = href;
	}
	/**
	 * 获取：
	 */
	public String getHref() {
		return href;
	}
	/**
	 * 设置：
	 */
	public void setShowCover(Integer showCover) {
		this.showCover = showCover;
	}
	/**
	 * 获取：
	 */
	public Integer getShowCover() {
		return showCover;
	}
	/**
	 * 设置：
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * 获取：
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * 设置：
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
	}
}
