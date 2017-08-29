package com.weiweb.gen.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.gen.model.TableEntity;

/** 代码生成器
 * 
  */
public interface SysGeneratorService {
	
    Map<String, String> queryTable(String tableName);
	
	List<Map<String, String>> queryColumns(String tableName);
	
	/** 生成代码 */
	byte[] generatorCode(String[] tableNames);
	
	Pagination<TableEntity> list(String findContent,ModelMap modelMap,Integer pageNo);
}
