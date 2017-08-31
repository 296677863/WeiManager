package com.weiweb.common.utils;

public enum OperType {

	OPER_TYPE_ADD("add", "添加"),

	OPER_TYPE_SAVEORUPDATE("add", "保存或更新"),

	OPER_TYPE_UPDATE("update", "修改"),

	OPER_TYPE_DELETE("delete", "删除"),

	OPER_TYPE_QUERY("query", "查询");

	public String	type;

	public String	desc;

	private OperType(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public static String getDesc(OperType type) {
		if (type == null) {
			return null;
		}
		for (OperType operType : OperType.values()) {
			if (type.type.equals(operType.type)) {
				return operType.desc;
			}
		}

		return null;
	}
}
