package com.weiweb.movie.domain;

import java.io.Serializable;


/**
 * 
 * 
 * @author zed
 * @email 296677863@qq.com
 * @date 2019-08-13
 */

public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long movieId;
	//
	private String movieName;
	//
	private String movieDirector;
	//
	private String movieLanguage;
	//
	private Long typeId;
	//
	private String movieDistrit;
	//
	private String movieDate;
	//
	private Integer isHead;
	//
	private Integer isImage;
	//
	private Integer isHot;
	//
	private String imageName;
	//
	private Long click;
	//
	private String url;

	/**
	 * 设置：
	 */
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	/**
	 * 获取：
	 */
	public Long getMovieId() {
		return movieId;
	}
	/**
	 * 设置：
	 */
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	/**
	 * 获取：
	 */
	public String getMovieName() {
		return movieName;
	}
	/**
	 * 设置：
	 */
	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}
	/**
	 * 获取：
	 */
	public String getMovieDirector() {
		return movieDirector;
	}
	/**
	 * 设置：
	 */
	public void setMovieLanguage(String movieLanguage) {
		this.movieLanguage = movieLanguage;
	}
	/**
	 * 获取：
	 */
	public String getMovieLanguage() {
		return movieLanguage;
	}
	/**
	 * 设置：
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	/**
	 * 获取：
	 */
	public Long getTypeId() {
		return typeId;
	}
	/**
	 * 设置：
	 */
	public void setMovieDistrit(String movieDistrit) {
		this.movieDistrit = movieDistrit;
	}
	/**
	 * 获取：
	 */
	public String getMovieDistrit() {
		return movieDistrit;
	}
	/**
	 * 设置：
	 */
	public void setMovieDate(String movieDate) {
		this.movieDate = movieDate;
	}
	/**
	 * 获取：
	 */
	public String getMovieDate() {
		return movieDate;
	}
	/**
	 * 设置：
	 */
	public void setIsHead(Integer isHead) {
		this.isHead = isHead;
	}
	/**
	 * 获取：
	 */
	public Integer getIsHead() {
		return isHead;
	}
	/**
	 * 设置：
	 */
	public void setIsImage(Integer isImage) {
		this.isImage = isImage;
	}
	/**
	 * 获取：
	 */
	public Integer getIsImage() {
		return isImage;
	}
	/**
	 * 设置：
	 */
	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}
	/**
	 * 获取：
	 */
	public Integer getIsHot() {
		return isHot;
	}
	/**
	 * 设置：
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	/**
	 * 获取：
	 */
	public String getImageName() {
		return imageName;
	}
	/**
	 * 设置：
	 */
	public void setClick(Long click) {
		this.click = click;
	}
	/**
	 * 获取：
	 */
	public Long getClick() {
		return click;
	}
	/**
	 * 设置：
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：
	 */
	public String getUrl() {
		return url;
	}
}
