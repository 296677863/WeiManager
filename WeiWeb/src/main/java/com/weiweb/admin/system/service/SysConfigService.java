package com.weiweb.admin.system.service;

import org.springframework.ui.ModelMap;

import com.weiweb.admin.system.model.SysConfig;
import com.weiweb.core.mybatis.page.Pagination;

public interface SysConfigService {

	Pagination<SysConfig> findByPage(ModelMap map, Integer pageNo, int pageSize);
}
