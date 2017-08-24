package com.weiweb.system.controller;


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
	
	/*****
	 * 字段管理删除
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Message delete(String[] ids ,RedirectAttributes redirectAttributes){
		sysDictService.deleteUserById(ids);
		return  SUCCESS_MESSAGE;
		
	}
	
	/*****
	 * 保存字典
	 * @return
	 */
	@RequestMapping(value = "/updateDict", method = RequestMethod.POST)
	@ResponseBody
	public Message updateSelectClass(SysDict sysDict){
		if(!StringUtils.isEmpty(sysDict.getDictId())){
			sysDictService.updateDict(sysDict);
		}else{
			sysDictService.saveDict(sysDict);
		}
		return  Message.success(sysDict);
	}
	
	/*****
	 * 保存字典详情
	 * @return
	 */
	@RequestMapping(value = "/updateDictDetail", method = RequestMethod.POST)
	@ResponseBody
	public Message updateSelectDetail(SysDictDetail sysDictDetail){
		if(!StringUtils.isEmpty(sysDictDetail.getDictType())){
			return ERROR_MESSAGE;
		}else{
			return ERROR_MESSAGE;
		}
	}
	
	/*****
	 * 字典管理详情删除
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/deleteDictDetail")
	@ResponseBody
	public Message deleteDictDetail(String id){
		if(!StringUtils.isEmpty(id)){
//			sysDictDetailService.delete(id);
			return  SUCCESS_MESSAGE;
		}else{
			return ERROR_MESSAGE;
		}
	}
	
	

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Message save(SysDict sysDict ,RedirectAttributes redirectAttributes) {
		return  SUCCESS_MESSAGE;
	}

	
	

}
