package com.weiweb.system.service;

import org.springframework.ui.ModelMap;

import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.system.model.SysConfig;

public interface SysConfigService {

	Pagination<SysConfig> findByPage(ModelMap map, Integer pageNo, int pageSize);
}
