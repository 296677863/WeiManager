package com.weiweb.permission.bo;

import java.io.Serializable;

import com.weiweb.common.model.UPermission;
import com.weiweb.common.utils.StringUtils;



public class UPermissionBo extends UPermission implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 是否勾选
	 */
	private String marker;
	/**
	 * role Id
	 */
	private String roleId;

	public boolean isCheck(){
		return StringUtils.equals(roleId,marker);
	}
	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
