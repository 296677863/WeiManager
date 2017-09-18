package com.weiweb.permission.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weiweb.common.controller.BaseController;
import com.weiweb.common.model.UPermission;
import com.weiweb.common.utils.LoggerUtils;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.core.shiro.po.Message;
import com.weiweb.permission.service.PermissionService;
import com.weiweb.system.bo.Menu;


@Controller
@Scope(value="prototype")
@RequestMapping("permission")
public class PermissionController extends BaseController {
	
	@Autowired
	PermissionService permissionService;
	/**
	 * 权限列表
	 * @param findContent	查询内容
	 * @param pageNo		页码
	 * @param modelMap		参数回显
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(){
		return "permission/list";
	}
	
	@RequestMapping(value="listData")
	@ResponseBody
	public Pagination<UPermission> index(String findContent,ModelMap modelMap,Integer pageNo){
		modelMap.put("findContent", findContent);
		Pagination<UPermission> permissions = permissionService.findPage(modelMap,pageNo,pageSize);
		return permissions;
	}
	/**
	 * 权限添加
	 * @param role
	 * @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	@ResponseBody
	public Message addPermission(UPermission psermission){
		try {
			if(psermission.getId()!=null){
				permissionService.updateByPrimaryKeySelective(psermission);
			}else{
				permissionService.insert(psermission);
			}
			return SUCCESS_MESSAGE;
		} catch (Exception e) {
			e.printStackTrace();
//			resultMap.put("status", 500);
//			resultMap.put("message", "添加失败，请刷新后再试！");
//			LoggerUtils.fmtError(getClass(), e, "添加权限报错。source[%s]", psermission.toString());
		}
		return ERROR_MESSAGE;
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(ModelMap model,@PathVariable("id") String permissionId){
		UPermission bean=permissionService.selectByPrimaryKey(new Long(permissionId));
		List<Menu> parents=permissionService.findParentMenus();
		model.addAttribute("bean", bean);
		model.addAttribute("parents", parents);
		return "permission/input";
	}
	
	@RequestMapping("/add")
	public String add(ModelMap model){
		List<Menu> parents=permissionService.findParentMenus();
		model.addAttribute("parents", parents);
		return "permission/input";
	}
	/**
	 * 删除权限，根据ID，但是删除权限的时候，需要查询是否有赋予给角色，如果有角色在使用，那么就不能删除。
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public Message deletePermissionById(String ids){
		return permissionService.deletePermissionById(ids);
	}
}
