package com.weiweb.gen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class SysGeneratorDaoImpl extends SqlSessionDaoSupport implements SysGeneratorDao{

	@Override
	public Map<String, String> queryTable(String tableName) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables ");
		sql.append(" where table_schema = (select database()) and table_name = '" + tableName + "'");
		Map<String, String> map = new LinkedHashMap<String, String>();
		
		Connection con=this.getSqlSession().getConnection();
		PreparedStatement  ps=null;
		try {
		    ps=con.prepareStatement(sql.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				map.put("tableName", rs.getString("tableName"));
				map.put("engine", rs.getString("engine"));
				map.put("tableComment", rs.getString("tableComment"));
				map.put("createTime", rs.getString("createTime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return map;
	}

	@Override
	public List<Map<String, String>> queryColumns(String tableName) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns");
		sql.append(" where table_name = '" + tableName + "' and table_schema = (select database()) order by ordinal_position");
		Connection con=this.getSqlSession().getConnection();
		PreparedStatement  ps=null;
		List<Map<String, String>> ls=new ArrayList<Map<String, String>>();
		try {
		    ps=con.prepareStatement(sql.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("columnName", rs.getString("columnName"));
				map.put("dataType", rs.getString("dataType"));
				map.put("columnComment", rs.getString("columnComment"));
				map.put("columnKey", rs.getString("columnKey"));
				map.put("extra", rs.getString("extra"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
	}

}
