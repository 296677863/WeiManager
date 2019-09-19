package com.weiweb.admin.gen.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weiweb.admin.gen.dao.SysGeneratorMapper;
import com.weiweb.admin.gen.model.TableEntity;
import com.weiweb.admin.gen.service.SysGeneratorService;
import com.weiweb.admin.gen.utils.GenUtils;
import com.weiweb.core.mybatis.BaseMybatisDao;
import com.weiweb.core.mybatis.page.Pagination;

@Service("sysGeneratorService")
public class SysGeneratorServiceImpl extends BaseMybatisDao<SysGeneratorMapper> implements SysGeneratorService{

	@Autowired
	private SysGeneratorMapper sysGeneratorMapper;
	
	@Override
	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		for (String tableName : tableNames) {
			// 查询表信息
			Map<String, String> table = queryTable(tableName);
			// 查询列信息
			List<Map<String, String>> columns = queryColumns(tableName);
			// 生成代码
			try {
				GenUtils.generatorCode(table, columns, zip);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

	@Override
	public Map<String, String> queryTable(String tableName) {
		return sysGeneratorMapper.queryTable(tableName);
	}
	
	@Override
	public List<Map<String, String>> queryColumns(String tableName) {
		return sysGeneratorMapper.queryColumns(tableName);
	}

	@Override
	@Transactional
	public Pagination<TableEntity> list(Map<String, Object> resultMap,
			Integer pageNo, Integer pageSize) {
		return super.findPage(resultMap, pageNo, pageSize);
	}
	

}
