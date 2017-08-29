package com.weiweb.gen.service;

import java.util.List;
import java.util.Map;

/** 代码生成器
 * 
  */
public interface SysGeneratorService {
	
    Map<String, String> queryTable(String tableName);
	
	List<Map<String, String>> queryColumns(String tableName);
	
	/** 生成代码 */
	byte[] generatorCode(String[] tableNames);
}
