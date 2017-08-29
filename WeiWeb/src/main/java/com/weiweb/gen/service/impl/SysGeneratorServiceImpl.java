package com.weiweb.gen.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.gen.dao.SysGeneratorDao;
import com.weiweb.gen.model.TableEntity;
import com.weiweb.gen.service.SysGeneratorService;
import com.weiweb.gen.utils.GenUtils;

@Service("sysGeneratorService")
public class SysGeneratorServiceImpl implements SysGeneratorService{

	@Autowired
	private SysGeneratorDao sysGeneratorDao;
	
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
		return sysGeneratorDao.queryTable(tableName);
	}
	
	@Override
	public List<Map<String, String>> queryColumns(String tableName) {
		return sysGeneratorDao.queryColumns(tableName);
	}

	@Override
	public Pagination<TableEntity> list(String findContent, ModelMap modelMap, Integer pageNo) {
		return sysGeneratorDao.list(findContent, modelMap, pageNo);
	}
	

}
