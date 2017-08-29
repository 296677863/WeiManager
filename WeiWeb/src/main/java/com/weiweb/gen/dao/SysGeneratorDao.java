package com.weiweb.gen.dao;

import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.gen.model.TableEntity;

public interface SysGeneratorDao {

	Map<String, String> queryTable(String tableName);
	
	List<Map<String, String>> queryColumns(String tableName);
	
	Pagination<TableEntity> list(String findContent,ModelMap modelMap,Integer pageNo);

	Pagination<TableEntity> list(String findContent, ModelMap modelMap, Integer pageNo,Integer pageSize);
}
