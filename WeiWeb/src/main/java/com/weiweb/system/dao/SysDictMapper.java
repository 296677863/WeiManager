package com.weiweb.system.dao;

import java.util.List;

import com.weiweb.system.model.SysDict;

public interface SysDictMapper{

	List<SysDict> findSysDictByType(String dictType);
	
}