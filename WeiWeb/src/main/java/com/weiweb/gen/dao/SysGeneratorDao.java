package com.weiweb.gen.dao;

import java.util.List;
import java.util.Map;

public interface SysGeneratorDao {

	Map<String, String> queryTable(String tableName);
	
	List<Map<String, String>> queryColumns(String tableName);
	
}
