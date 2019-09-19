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

import com.weiweb.admin.permission.bo.URoleBo;
import com.weiweb.admin.permission.bo.UserRoleAllocationBo;
import com.weiweb.admin.permission.service.PermissionService;
import com.weiweb.admin.user.service.UUserService;
import com.weiweb.common.controller.BaseController;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.core.shiro.po.Message;


@Controller
@Scope(value="prototype")
@RequestMapping("role")
public class UserRoleAllocationController extends BaseController {
	@Autowired
	UUserService userService;
	@Autowired
	PermissionService permissionService;
	/**
	 * 用户角色权限分配
	 * @param modelMap
	 * @param pageNo
	 * @param findContent
	 * @return
	 */
	@RequestMapping(value="allocation")
	public ModelAndView allocation(){
		return new ModelAndView("role/allocation");
	}
	
	@RequestMapping("allocationData")
	@ResponseBody
	public Pagination<UserRoleAllocationBo> allocationData(ModelMap modelMap,Integer pageNo,String findContent){
		modelMap.put("findContent", findContent);
		Pagination<UserRoleAllocationBo> boPage = userService.findUserAndRole(modelMap,pageNo,pageSize);
		return boPage;
	}
	
	
	/**
	 * 根据用户ID查询权限
	 * @param id
	 * @return
	 */
	@RequestMapping(value="selectRole/{userId}")
	public String selectRoleByUserId(@PathVariable("userId") String id,ModelMap model){
		List<URoleBo> bos = userService.selectRoleByUserId(new Long(id));
		model.addAttribute("userId", id);
		model.addAttribute("bos", bos);
		return "role/selectRole";
	}
	/**
	 * 操作用户的角色
	 * @param userId 	用户ID
	 * @param ids		角色ID，以‘,’间隔
	 * @return
	 */
	@RequestMapping(value="addRole2User")
	@ResponseBody
	public Message addRole2User(Long userId,String ids){
		return userService.addRole2User(userId,ids);
	}
	/**
	 * 根据用户id清空角色。
	 * @param userIds	用户ID ，以‘,’间隔
	 * @return
	 */
	@RequestMapping(value="clearRoleByUserIds")
	@ResponseBody
	public Map<String,Object> clearRoleByUserIds(String userIds){
		return userService.deleteRoleByUserIds(userIds);
	}
}
