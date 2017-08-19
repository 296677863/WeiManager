package com.weiweb.common.model;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;


public class UPermission implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	
	private Date createDate;
	private Date modifyDate;
	
	private String code; //权限代码
    /**排序号,不能为空**/
	private Integer sort;
    /**层级**/
	private Integer level;
	/**菜单名称**/
	private String name;
    /**链接**/
	private String url;
    /**打开目标**/
	private String target;
    
	private String iconId;
    /**上级菜单**/
	private Long parent; //上级菜单
    
	private Boolean status;
    
	private Boolean dataLevel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIconId() {
		return iconId;
	}

	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getDataLevel() {
		return dataLevel;
	}

	public void setDataLevel(Boolean dataLevel) {
		this.dataLevel = dataLevel;
	}

	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}