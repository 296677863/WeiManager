package com.weiweb.admin.system.service;

import java.util.List;

import org.springframework.ui.ModelMap;

import com.weiweb.admin.system.model.SysDict;
import com.weiweb.core.mybatis.page.Pagination;

public interface SysDictService {

	Pagination<SysDict> findByPage(ModelMap map, Integer pageNo, int pageSize);

	List<SysDict> findSysDictByType(String dictType);


	int deleteByPrimaryKey(String id);

	void updateDict(SysDict sysDict);

	void saveDict(SysDict sysDict);

	void deleteIds(String[] ids);

}
