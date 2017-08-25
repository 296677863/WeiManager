package com.weiweb.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.weiweb.core.mybatis.BaseMybatisDao;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.system.dao.SysConfigMapper;
import com.weiweb.system.model.SysConfig;
import com.weiweb.system.service.SysConfigService;

@Service
public class SysConfigServiceImpl  extends BaseMybatisDao<SysConfigMapper> implements SysConfigService {

	@Override
	public Pagination<SysConfig> findByPage(ModelMap map, Integer pageNo, int pageSize) {
		return super.findPage(map, pageNo, pageSize);
	}

	

}
