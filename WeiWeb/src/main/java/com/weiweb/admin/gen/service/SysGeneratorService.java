package com.weiweb.admin.gen.service;

import java.util.List;
import java.util.Map;

import com.weiweb.admin.gen.model.TableEntity;
import com.weiweb.core.mybatis.page.Pagination;

/** 代码生成器
 * 
  */
public interface SysGeneratorService {
	
    Map<String, String> queryTable(String tableName);
	
	List<Map<String, String>> queryColumns(String tableName);
	
	/** 生成代码 */
	byte[] generatorCode(String[] tableNames);
	
	Pagination<TableEntity> list(Map<String, Object> resultMap,
			Integer pageNo, Integer pageSize);
}
