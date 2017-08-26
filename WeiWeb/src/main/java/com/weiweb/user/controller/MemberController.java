package com.weiweb.user.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.weiweb.common.controller.BaseController;
import com.weiweb.common.model.UUser;
import com.weiweb.common.utils.StringUtils;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.core.shiro.po.Message;
import com.weiweb.core.shiro.session.CustomSessionManager;
import com.weiweb.user.bo.UserOnlineBo;
import com.weiweb.user.manager.UserManager;
import com.weiweb.user.service.UUserService;

@Controller
@Scope(value="prototype")
@RequestMapping("member")
public class MemberController extends BaseController {
	/***
	 * 用户手动操作Session
	 * */
	@Autowired
	CustomSessionManager customSessionManager;
	@Autowired
	UUserService userService;
	/**
	 * 用户列表管理
	 * @return
	 */
	@RequestMapping(value="list")
	public ModelAndView list(ModelMap map,Integer pageNo,String findContent){
		return new ModelAndView("member/list");
	}
	
	@RequestMapping("/listData")
	@ResponseBody
	public Pagination<UUser> listData(UUser user,Integer pageNo, ModelMap map){
		map.put("nickname", user.getNickname());
		map.put("email", user.getEmail());
		Pagination<UUser> page = userService.findByPage(map,pageNo,pageSize);
		return page;
		

	}
	
	
	/**
	 * 在线用户管理
	 * @return
	 */
	@RequestMapping(value="online")
	public ModelAndView online(){
		List<UserOnlineBo> list = customSessionManager.getAllUser();
		return new ModelAndView("member/online","list",list);
	}
	
	
	/**
	 * 在线用户详情
	 * @return
	 */
	@RequestMapping(value="onlineDetails/{sessionId}",method=RequestMethod.GET)
	public ModelAndView onlineDetails(@PathVariable("sessionId")String sessionId){
		UserOnlineBo bo = customSessionManager.getSession(sessionId);
		return new ModelAndView("member/onlineDetails","bo",bo);
	}
	/**
	 * 改变Session状态
	 * @param status
	 * @param sessionId
	 * @return
	 */
	@RequestMapping(value="changeSessionStatus",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> changeSessionStatus(Boolean status,String sessionIds){
		return customSessionManager.changeSessionStatus(status,sessionIds);
	}
	/**
	 * 根据ID删除，
	 * @param ids	如果有多个，以“,”间隔。
	 * @return
	 */
	@RequestMapping(value="deleteUserById",method=RequestMethod.POST)
	@ResponseBody
	public Message deleteUserById(String ids){
		if( userService.deleteUserById(ids)){
			return ERROR_MESSAGE;
		}else{
			return SUCCESS_MESSAGE;
		}
	}
	/**
	 * 禁止登录
	 * @param id		用户ID
	 * @param status	1:有效，0:禁止登录
	 * @return
	 */
	@RequestMapping(value="forbidUserById",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> forbidUserById(String id,String status){
		Map<String,Object> map=userService.updateForbidUserById(Long.valueOf(id),Long.valueOf(status));
		return  map;
	}
	

	@RequestMapping("/add")
	public String add( ModelMap model){
		return "/member/add";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit( @PathVariable("id")String id,ModelMap model){
		if(!StringUtils.isEmpty(id))
			model.addAttribute("bean",userService.selectByPrimaryKey(Long.parseLong(id)));
		return "/member/edit";
	}
	@RequestMapping("/save")
	@ResponseBody
	public Message save(UUser user,RedirectAttributes redirectAttributes){
		String pswd=UserManager.md5Pswd(user.getEmail(), user.getPswd());
		user.setPswd(pswd);
		user.setLastLoginTime(null);
		user.setCreateTime(new Date());
		try {
			userService.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR_MESSAGE;
		}
		return Message.success(user);
	}
	
	
	/**
	 * 检查用户名是否存在
	 */
	@RequestMapping(value = "/check_email", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkUsername(String email,String id) {
		if (StringUtils.isEmpty(email)) {
			return false;
		}
		boolean isHas = true;
		List<UUser> list = userService.findUserByEmail(email);
		//添加时
		if(StringUtils.isEmpty(id)){
			return CollectionUtils.isEmpty(list);
		}
		for (UUser user : list) {
			if(!user.getId().equals(id)){
				isHas=  false;
				break;
			}
			
		}
		return isHas;
	}
	
	@RequestMapping("/info/{id}")
	public String info( @PathVariable("id")String id,ModelMap model){
		if(!StringUtils.isEmpty(id))
			model.addAttribute("bean",userService.selectByPrimaryKey(Long.parseLong(id)));
		return "/member/info";
	}
	
	
}
