package com.weiweb.weixin.dao;

import com.weiweb.weixin.model.WxImgArticle;


/**
 * 
 * 
 * @author zed
 * @email 296677863@qq.com
 * @date 2017-09-01
 */
public interface WxImgArticleMapper {

	int deleteByPrimaryKey(String id);
	
	int insert(WxImgArticle record);
	 
	int insertSelective(WxImgArticle record); 
	
	WxImgArticle selectByPrimaryKey(String id);
	
	int updateByPrimaryKeySelective(WxImgArticle record);

	int updateByPrimaryKey(WxImgArticle record);
	 
	
}
