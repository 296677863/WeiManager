package com.weiweb.admin.weixin.service;

import java.util.Map;

import com.weiweb.admin.weixin.model.WxImgArticle;
import com.weiweb.core.mybatis.page.Pagination;


/**
 * 
 * 
 * @author zed
 * @email 296677863@qq.com
 * @date 2017-09-01
 */
public interface WxImgArticleService {

    Pagination<WxImgArticle> findPage(Map<String, Object> resultMap,
			Integer pageNo, Integer pageSize);
	
	
	int deleteByPrimaryKey(String id);
	
	
	int insert(WxImgArticle record);
	
	int insertSelective(WxImgArticle record);
		
	WxImgArticle selectByPrimaryKey(String id);
	
	
	int updateByPrimaryKeySelective(WxImgArticle  record);

    int updateByPrimaryKey(WxImgArticle  record);


	boolean deletewxImgArticleByIds(String[] arryids);
	
	 
}
