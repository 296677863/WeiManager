package com.weiweb.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weiweb.core.mybatis.BaseMybatisDao;
import com.weiweb.system.dao.SysDictDetailMapper;
import com.weiweb.system.model.SysDictDetail;
import com.weiweb.system.service.SysDictDetailService;

@Service
public class SysDictDetailServiceImpl extends BaseMybatisDao<SysDictDetailMapper>  implements SysDictDetailService{
    
	@Autowired
	SysDictDetailMapper sysDictDetailMapper;
	
	@Override
	public void saveDictDetail(SysDictDetail dictDetail) {
		sysDictDetailMapper.insert(dictDetail);
		
	}

	@Override
	public void updateDictDetail(SysDictDetail dictDetail) {
		sysDictDetailMapper.updateByPrimaryKeySelective(dictDetail);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return sysDictDetailMapper.deleteByPrimaryKey(id);
	}
	

}
