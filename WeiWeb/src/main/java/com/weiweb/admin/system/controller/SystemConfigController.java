package com.weiweb.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weiweb.admin.system.model.SysConfig;
import com.weiweb.admin.system.service.SysConfigService;
import com.weiweb.common.controller.BaseController;
import com.weiweb.core.mybatis.page.Pagination;
@Controller
@RequestMapping("/sysconfig")
public class SystemConfigController extends BaseController{
	
	@Autowired
	SysConfigService sysConfigService;

	@RequestMapping("/list")
	public String list() {
		return "/sysconfig/list";
	}
	
	@RequestMapping("/getSysConfigs")
	@ResponseBody
	public Pagination<SysConfig> getSysConfigs(SysConfig sysConfig,Integer pageNo, ModelMap map){
		return sysConfigService.findByPage(map, pageNo, pageSize);
		
	}
	
}
	
	