package com.weiweb.admin.system.dao;

import java.util.List;

import com.weiweb.admin.system.model.SysDict;

public interface SysDictMapper{

	List<SysDict> findSysDictByType(String dictType);

	int deleteByPrimaryKey(String id);
	
	
	int insert(SysDict record);

    int insertSelective(SysDict record);


    int updateByPrimaryKeySelective(SysDict record);

    int updateByPrimaryKey(SysDict record);
    SysDict selectByPrimaryKey(String dictId);
}