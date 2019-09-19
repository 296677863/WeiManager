package com.weiweb.admin.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.weiweb.admin.permission.service.PermissionService;
import com.weiweb.admin.permission.service.RoleService;
import com.weiweb.admin.user.manager.UserManager;
import com.weiweb.admin.user.service.UUserService;
import com.weiweb.common.controller.BaseController;
import com.weiweb.common.model.UPermission;
import com.weiweb.common.model.URole;
import com.weiweb.common.model.UUser;
import com.weiweb.common.utils.LoggerUtils;
import com.weiweb.common.utils.StringUtils;
import com.weiweb.core.shiro.po.Message;
import com.weiweb.core.shiro.token.manager.TokenManager;

import net.sf.json.JSONObject;



@Controller
@Scope(value="prototype")
@RequestMapping("admin/user")
public class UserCoreController extends BaseController {

	@Resource
	UUserService userService;
	@Resource
	RoleService roleService;
	@Resource
	PermissionService permissionService;
	/**
	 * 个人资料
	 * @return
	 */
	@RequestMapping(value="index",method=RequestMethod.GET)
	public ModelAndView userIndex(ModelAndView view,HttpServletRequest request){
		String skin=(String) request.getSession().getAttribute("skin");
		if(StringUtils.isNotBlank(skin)){

			request.getSession().setAttribute("skin",skin);
		}else{

			request.getSession().setAttribute("skin","default");
		}

		List<URole> roles=roleService.findNowAllPermission();
		if(roles!=null&&roles.size()!=0){
			List<UPermission> UPermissions=roles.get(0).getPermissions();
			view.addObject("menus", UPermissions);
		}
		view.addObject("flag", "true");
		view.addObject("main_top_name", "微管理");
		view.setViewName("/admin/user/index");
		return view;
	}
	
	
	/**
	 * 偷懒一下，通用页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping(value="{page}",method=RequestMethod.GET)
	public ModelAndView toPage(@PathVariable("page")String page){
		return new ModelAndView(String.format("admin/user/%s", page));
	}
	
	@RequestMapping("/editpwd")
	public String editpwd(){
		return "/admin/user/editpwd";
	}
	
	/**
	 * 检查旧密码
	 */
	@RequestMapping(value = "/checkPwd")
	public @ResponseBody
	boolean checkPwd(String pwd) {
		String email = TokenManager.getToken().getEmail();
		pwd = UserManager.md5Pswd(email, pwd);
		UUser user = userService.login(email, pwd);
		if (StringUtils.isEmpty(pwd)) {
			return false;
		}
		boolean result = true;
		if(!(pwd).equals(user.getPswd())){
			result = false;
		}
		return result;
	}
	
	
	/**
	 * 修改密码
	 * @param admin
	 * @return
	 */
	@RequestMapping("/updatePwd")
	@ResponseBody
	public Message updatePwd(String pwd,String newPwd){
		try {
			String email = TokenManager.getToken().getEmail();
			pwd = UserManager.md5Pswd(email, pwd);
			UUser user = userService.login(email, pwd);
			user.setPswd(newPwd);
			user = UserManager.md5Pswd(user);
			userService.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("fail");
		}
		return Message.success("success");
	}
	
	/**
	 * 个人资料修改
	 * @return
	 */
	@RequestMapping(value="updateSelf",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateSelf(UUser entity){
		try {
			userService.updateByPrimaryKeySelective(entity);
			resultMap.put("status", 200);
			resultMap.put("message", "修改成功!");
		} catch (Exception e) {
			resultMap.put("status", 500);
			resultMap.put("message", "修改失败!");
			LoggerUtils.fmtError(getClass(), e, "修改个人资料出错。[%s]", JSONObject.fromObject(entity).toString());
		}
		return resultMap;
	}
}
