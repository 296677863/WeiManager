package com.weiweb.permission.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.weiweb.common.controller.BaseController;
import com.weiweb.common.model.URole;
import com.weiweb.common.model.UUser;
import com.weiweb.common.utils.LoggerUtils;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.core.shiro.po.Message;
import com.weiweb.permission.service.RoleService;
import com.weiweb.user.manager.UserManager;
import com.weiweb.user.service.UUserService;


@Controller
@Scope(value="prototype")
@RequestMapping("role")
public class RoleController extends BaseController {
	@Autowired
	RoleService roleService;
	
	@Autowired
	UUserService userService;
	/**
	 * 角色列表
	 * @return
	 */
	
	@RequestMapping(value="list")
	public String list(){
		return "role/list";
	}
	@RequestMapping(value="listData")
	@ResponseBody
	public Pagination<URole> index(String findContent,ModelMap modelMap){
		modelMap.put("findContent", findContent);
		Pagination<URole> role = roleService.findPage(modelMap,pageNo,pageSize);
		return role;
	}
	
	/***
	 * 添加角色
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add( ModelMap model){
		return "/role/edit";
	}
	
	@RequestMapping("memberList/{roleId}")
	public String memberList(@PathVariable("roleId") String roleId){
		return "role/memberList";
	}
	
	@RequestMapping("memberListData")
	@ResponseBody
	public  Pagination<UUser> memberListData(String roleId,ModelMap modelMap){
		modelMap.put("roleId", roleId);
		return userService.findUserByroleId(modelMap, pageNo, pageSize);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(URole role) {
		try {
			roleService.updateByPrimaryKeySelective(role);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR_MESSAGE;
		}
		return Message.success(role);
	}
	/**
	 * 角色添加
	 * @param role
	 * @return
	 */
	@RequestMapping(value="addRole",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addRole(URole role){
		try {
			int count = roleService.insertSelective(role);
			resultMap.put("status", 200);
			resultMap.put("successCount", count);
		} catch (Exception e) {
			resultMap.put("status", 500);
			resultMap.put("message", "添加失败，请刷新后再试！");
			LoggerUtils.fmtError(getClass(), e, "添加角色报错。source[%s]",role.toString());
		}
		return resultMap;
	}
	
	@RequestMapping("/edit/{id}")
	public String input(@PathVariable("id") String id,  ModelMap model){
		URole role=roleService.findAllPermissionById(Long.parseLong(id));
		model.addAttribute("bean",role);
		model.addAttribute("menus", role.getPermissions());
		return "/role/edit";
	}
	
	@RequestMapping("/info/{id}")
	public String info(@PathVariable("id") String id,  ModelMap model){
		URole role=roleService.findAllPermissionById(Long.parseLong(id));
		model.addAttribute("bean",role);
		model.addAttribute("menus", role.getPermissions());
		return "/role/info";
	}
	/**
	 * 删除角色，根据ID，但是删除角色的时候，需要查询是否有赋予给用户，如果有用户在使用，那么就不能删除。
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteRoleById(String ids){
		return roleService.deleteRoleById(ids);
	}
	/**
	 * 我的权限页面
	 * @return
	 */
	@RequestMapping(value="mypermission",method=RequestMethod.GET)
	public ModelAndView mypermission(){
		return new ModelAndView("permission/mypermission");
	}
	/**
	 * 我的权限 bootstrap tree data
	 * @return
	 */
	@RequestMapping(value="getPermissionTree",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getPermissionTree(){
		//查询我所有的角色 ---> 权限
		List<URole> roles = roleService.findNowAllPermission();
		//把查询出来的roles 转换成bootstarp 的 tree数据
		List<Map<String, Object>> data = UserManager.toTreeData(roles);
		return data;
	}
}
