package com.weiweb.system.service;

import java.util.List;

import org.springframework.ui.ModelMap;

import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.system.model.SysDict;

public interface SysDictService {

	Pagination<SysDict> findByPage(ModelMap map, Integer pageNo, int pageSize);

	List<SysDict> findSysDictByType(String dictType);

	void deleteUserById(String[] ids);

	int deleteByPrimaryKey(String id);

	void updateDict(SysDict sysDict);

	void saveDict(SysDict sysDict);

}
