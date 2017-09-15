package com.weiweb.system.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.weiweb.core.mybatis.BaseMybatisDao;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.core.shiro.cache.VCache;
import com.weiweb.system.dao.SysConfigMapper;
import com.weiweb.system.model.SysConfig;
import com.weiweb.system.service.SysConfigService;

@Service
@Lazy(false)
public class SysConfigServiceImpl  extends BaseMybatisDao<SysConfigMapper> implements SysConfigService, ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	SysConfigMapper sysConfigMapper;
	
	@Override
	public Pagination<SysConfig> findByPage(ModelMap map, Integer pageNo, int pageSize) {
		return super.findPage(map, pageNo, pageSize);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		   if(event.getApplicationContext().getParent() == null)//root application context 没有parent，他就是老大.
	        {  
			   /*************************************系统参数初始化*********************************************/
				List<SysConfig> systemConfigs = sysConfigMapper.findAll();
			    for (SysConfig systemConfig : systemConfigs) {  
			    	VCache.setVByMap("sysMap", systemConfig.getParamName(), systemConfig);
			    } 
	            System.out.println("\n\n\n\n\n______________\n\n\n加载了\n\n_________\n\n");
	        }  
	        
	}
	

}
