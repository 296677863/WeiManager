package com.weiweb.common.dao;

import java.util.List;
import java.util.Map;

import com.weiweb.common.model.UploadFile;


/**
 * 
 * 
 * @author zed
 * @email 296677863@qq.com
 * @date 2017-09-15
 */
public interface UploadFileMapper {

	int deleteByPrimaryKey(String id);
	
	int insert(UploadFile record);
	 
	int insertSelective(UploadFile record); 
	
	UploadFile selectByPrimaryKey(String id);
	
	int updateByPrimaryKeySelective(UploadFile record);

	int updateByPrimaryKey(UploadFile record);
	
	List<UploadFile> selectByFilePath(String filePath);
	
	List<UploadFile> selectByRelationId(String relationId);
	
	List<UploadFile> findByUploadFile(Map<String,Object> map);
}
