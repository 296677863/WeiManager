package com.weiweb.weixin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weiweb.core.mybatis.BaseMybatisDao;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.weixin.dao.WxImgArticleMapper;
import com.weiweb.weixin.model.WxImgArticle;
import com.weiweb.weixin.service.WxImgArticleService;




@Service
public class WxImgArticleServiceImpl extends BaseMybatisDao<WxImgArticleMapper> implements WxImgArticleService {
	@Autowired
	private WxImgArticleMapper wxImgArticleMapper;
	
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return wxImgArticleMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int insert(WxImgArticle record) {
		return wxImgArticleMapper.insert(record);
	}
	
	@Override
	public int insertSelective(WxImgArticle record) {
		return wxImgArticleMapper.insertSelective(record);
	}
	
	@Override
	public WxImgArticle selectByPrimaryKey(String id) {
		return wxImgArticleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(WxImgArticle record) {
		return wxImgArticleMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(WxImgArticle record) {
		return wxImgArticleMapper.updateByPrimaryKeySelective(record);
	}

	
	@Override
	public Pagination<WxImgArticle> findPage(Map<String, Object> resultMap,
			Integer pageNo, Integer pageSize) {
		return super.findPage(resultMap, pageNo, pageSize);
	}

	@Override
	@Transactional
	public boolean deletewxImgArticleByIds(String[] arryids) {
		for(String id:arryids){
			wxImgArticleMapper.deleteByPrimaryKey(id);
		}
		return true;
	}

	
	
}
