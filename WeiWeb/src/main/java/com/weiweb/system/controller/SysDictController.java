package com.weiweb.system.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.druid.util.StringUtils;
import com.weiweb.common.controller.BaseController;
import com.weiweb.common.utils.UUIDHelper;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.core.shiro.po.Message;
import com.weiweb.system.model.SysDict;
import com.weiweb.system.model.SysDictDetail;
import com.weiweb.system.service.SysDictDetailService;
import com.weiweb.system.service.SysDictService;

@Controller
@RequestMapping("/sysdict")
public class SysDictController  extends BaseController {
	
	@Autowired
	SysDictService sysDictService;
	
	@Autowired
	SysDictDetailService sysDictDetailService;
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
	@RequestMapping("/edit")
	public String input(String dictType,ModelMap model){
		List<SysDict> sysDicts=sysDictService.findSysDictByType(dictType);
		if(sysDicts!=null&&sysDicts.size()!=0){
			model.addAttribute("bean",sysDicts.get(0));
		}
		
		return "/sysdict/edit";
	}
	
	@RequestMapping("/info")
	public String info(String dictType,ModelMap model){
		List<SysDict> sysDicts=sysDictService.findSysDictByType(dictType);
		if(sysDicts!=null&&sysDicts.size()!=0){
			model.addAttribute("bean",sysDicts.get(0));
		}
		return "/sysdict/info";
	}
	
	/*****
	 * 字段管理删除
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Message delete(String[] ids ,RedirectAttributes redirectAttributes){
		sysDictService.deleteIds(ids);
		return  SUCCESS_MESSAGE;
		
	}
	
	/*****
	 * 保存字典
	 * @return
	 */
	@RequestMapping(value = "/updateDict", method = RequestMethod.POST)
	@ResponseBody
	public Message updateDict(SysDict sysDict){
		if(!StringUtils.isEmpty(sysDict.getDictId())){
			sysDictService.updateDict(sysDict);
		}else{
			String dictId=UUIDHelper.gen();
			sysDict.setDictId(dictId);
			sysDictService.saveDict(sysDict);
		}
		return Message.success(sysDict);
	}
	

	
	

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Message save(SysDict sysDict ,RedirectAttributes redirectAttributes) {
		String dictId=UUIDHelper.gen();
		sysDict.setDictId(dictId);
		sysDictService.saveDict(sysDict);
		return  Message.success(sysDict);
	}

	
	

}
