package com.weiweb.admin.system.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class SysDict implements Serializable{

	private static final long serialVersionUID = 8630423462074986692L;
	//主键
	private String dictId;
	//名称
	private String dictName;
	//类型
	private String dictType;
	//备注
	private String dictRemark;
	
	private List<SysDictDetail> sysDictDetails = new LinkedList<SysDictDetail>();;
	
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public String getDictType() {
		return dictType;
	}
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	public String getDictRemark() {
		return dictRemark;
	}
	public void setDictRemark(String dictRemark) {
		this.dictRemark = dictRemark;
	}
	public List<SysDictDetail> getSysDictDetails() {
		return sysDictDetails;
	}
	public void setSysDictDetails(List<SysDictDetail> sysDictDetails) {
		this.sysDictDetails = sysDictDetails;
	}
	
	
	
}