package com.weiweb.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weiweb.common.controller.BaseController;
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
	public Pagination<SysDict> listData(SysDict sysDict,Integer pageNo, ModelMap map){
		Pagination<SysDict> page = sysDictService.findByPage(map,pageNo,pageSize);
		return page;
	}
	
	/****
	 * 字段管理添加
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(ModelMap model){
		return "/sysdict/add";
	}
	
	/*****
	 * 字典管理编辑
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit/{dictType}")
	public String input(@PathVariable("dictType")String dictType,ModelMap model){
		model.addAttribute("bean",sysDictService.findSysDictByType(dictType));
		return "/sysdict/edit";
	}
	
	@RequestMapping("/info/{dictType}")
	public String info(@PathVariable("dictType")String dictType,ModelMap model){
		model.addAttribute("bean",sysDictService.findSysDictByType(dictType));
		return "/sysdict/info";
	}
	

}
