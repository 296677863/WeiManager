package com.weiweb.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.weiweb.core.mybatis.BaseMybatisDao;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.core.shiro.cache.VCache;
import com.weiweb.core.shiro.cache.impl.SystemConfigCache;
import com.weiweb.system.dao.SysConfigMapper;
import com.weiweb.system.model.SysConfig;
import com.weiweb.system.service.SysConfigService;

@Service
@Lazy(false)
public class SysConfigServiceImpl  extends BaseMybatisDao<SysConfigMapper> implements SysConfigService {

	@Autowired
	SysConfigMapper sysConfigMapper;
	
	@Override
	public Pagination<SysConfig> findByPage(ModelMap map, Integer pageNo, int pageSize) {
		return super.findPage(map, pageNo, pageSize);
	}

	@PostConstruct
	public void initSystemConfig(){
		/*************************************系统参数初始化*********************************************/
		List<SysConfig> systemConfigs = sysConfigMapper.findAll();
	    for (SysConfig systemConfig : systemConfigs) {  
	    	VCache.setVByMap("sysMap", systemConfig.getParamName(), systemConfig);
	    }  
	}
	

}
