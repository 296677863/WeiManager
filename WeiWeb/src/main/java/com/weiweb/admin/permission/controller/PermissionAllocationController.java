package com.weiweb.admin.permission.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.weiweb.admin.permission.bo.UPermissionBo;
import com.weiweb.admin.permission.service.PermissionService;
import com.weiweb.admin.permission.service.RoleService;
import com.weiweb.common.controller.BaseController;
import com.weiweb.core.shiro.po.Message;


@Controller
@Scope(value="prototype")
@RequestMapping("permission")
public class PermissionAllocationController extends BaseController {
	
	@Autowired
	PermissionService permissionService;
	@Autowired
	RoleService roleService;
	/**
	 * 权限分配
	 * @param modelMap
	 * @param pageNo
	 * @param findContent
	 * @return
	 */
	@RequestMapping(value="allocation/{roleId}")
	public ModelAndView allocation(@PathVariable("roleId") String roleId,ModelMap model){
		List<UPermissionBo> permissionBos = permissionService.selectPermissionById(new Long(roleId));
		model.addAttribute("roleId", roleId);
		model.addAttribute("permissions", permissionBos);
		return new ModelAndView("permission/allocation");
	}
	

	/**
	 * 操作角色的权限
	 * @param roleId 	角色ID
	 * @param ids		权限ID，以‘,’间隔
	 * @return
	 */
	@RequestMapping(value="addPermission2Role")
	@ResponseBody
	public Message addPermission2Role(Long roleId,String ids){
		return permissionService.addPermission2Role(roleId,ids);
	}
	/**
	 * 根据角色id清空权限。
	 * @param roleIds	角色ID ，以‘,’间隔
	 * @return
	 */
	@RequestMapping(value="clearPermissionByRoleIds")
	@ResponseBody
	public Map<String,Object> clearPermissionByRoleIds(String roleIds){
		return permissionService.deleteByRids(roleIds);
	}
}
