package com.weiweb.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weiweb.common.controller.BaseController;
import com.weiweb.common.model.UUser;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.system.model.SysDict;
import com.weiweb.system.service.SysDictService;

@Controller
@RequestMapping("/sysdict")
public class SysDictController  extends BaseController {
	
	@Autowired
	SysDictService sysDictService;
	/***
	 * 页面跳转
	 * @return
	 */
	@RequestMapping("/list")
	public String list() {
		return "/sysdict/list";
	}
	
	@RequestMapping("/listData")
	@ResponseBody
	public Pagination<SysDict> listData(Integer pageNo, ModelMap map){
		Pagination<SysDict> page = sysDictService.findByPage(map,pageNo,pageSize);
		return page;
		

	}

}
