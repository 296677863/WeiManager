package com.weiweb.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.weiweb.common.dao.UUserMapper;
import com.weiweb.core.mybatis.BaseMybatisDao;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.system.dao.SysDictDetailMapper;
import com.weiweb.system.dao.SysDictMapper;
import com.weiweb.system.model.SysDict;
import com.weiweb.system.service.SysDictService;

public class SysDictServiceImpl extends BaseMybatisDao<SysDictMapper>  implements SysDictService{
	@Autowired
	SysDictMapper sysDictMapper;
	@Autowired
	SysDictDetailMapper sysDictDetailMapper;

	@Override
	public Pagination<SysDict> findByPage(ModelMap map, Integer pageNo, int pageSize) {
		return super.findPage(map, pageNo, pageSize);
	}

}
