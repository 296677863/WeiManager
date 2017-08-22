package com.weiweb.system.model;

import java.io.Serializable;

public class SysDictDetail implements Serializable{
	
	private static final long serialVersionUID = -5852658153403782319L;
	/*
	  `detail_id` varchar(32) NOT NULL COMMENT '主键',
	  `dict_type` varchar(64) DEFAULT NULL COMMENT '数据字典类型',
	  `detail_name` varchar(256) DEFAULT NULL COMMENT '名称',
	  `detail_code` varchar(32) DEFAULT NULL COMMENT '代码',
	  `detail_sort` varchar(32) DEFAULT NULL COMMENT '排序号',
	  `detail_type` varchar(32) DEFAULT NULL COMMENT '类型',
	  `detail_state` varchar(32) DEFAULT NULL COMMENT '状态',
	  `detail_content` varchar(256) DEFAULT NULL COMMENT '内容',
	  `detail_remark` varchar(256) DEFAULT NULL COMMENT '备注',
	  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
	  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
	  */
	private String detailId;
	
	private String dictType;
	
	private String detailName;
	
	private String detailCode;
	
	private String detailSort;
	
	private String detailType;
	
	private String detailState;
	
	private String detailContent;
	
	private String detailRemark;
	
	private String createTime;
	
	private Long createId;

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	public String getDetailCode() {
		return detailCode;
	}

	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}

	public String getDetailSort() {
		return detailSort;
	}

	public void setDetailSort(String detailSort) {
		this.detailSort = detailSort;
	}

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	public String getDetailState() {
		return detailState;
	}

	public void setDetailState(String detailState) {
		this.detailState = detailState;
	}

	public String getDetailContent() {
		return detailContent;
	}

	public void setDetailContent(String detailContent) {
		this.detailContent = detailContent;
	}

	public String getDetailRemark() {
		return detailRemark;
	}

	public void setDetailRemark(String detailRemark) {
		this.detailRemark = detailRemark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}
	
	
	
	
}