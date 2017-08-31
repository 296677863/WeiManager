package com.weiweb.weixin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.http.weixin.ApiResult;
import com.foxinmy.weixin4j.model.Button;
import com.foxinmy.weixin4j.mp.WeixinProxy;
import com.weiweb.common.controller.BaseController;
import com.weiweb.common.utils.OperType;
import com.weiweb.core.annotation.UserLogAnno;
import com.weiweb.core.shiro.po.Message;

@Controller
@RequestMapping("/base/wxmenu")
public class WxMenuController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(WxMenuController.class);
	@Resource
	public WeixinProxy weixinProxy;

	/**
	 * 微信自定义菜单配置页面
	 * 
	 * @return
	 * @throws WeixinException
	 */
	@RequestMapping("/index")
	@UserLogAnno(model = "wxCreateMenu", operDesc = "微信自定义菜单配置页面", operType = OperType.OPER_TYPE_QUERY)
	public String printIndex(ModelMap model) throws WeixinException {
		try {
			List<Button> buttons = weixinProxy.getMenu();
			model.addAttribute("buttons", buttons);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/weixin/wxMenu/wxMenu_index";
	}

	/**
	 * 修改菜单
	 * 
	 * @return
	 * @throws WeixinException
	 */
	@RequestMapping("/update")
	@ResponseBody
	@UserLogAnno(model = "wxCreateMenu", operDesc = "修改微信菜单配置", operType = OperType.OPER_TYPE_UPDATE)
	public Message printIndex(String jsonArr) throws WeixinException {
		List<Button> menuSettings2 = JSON.parseArray(jsonArr, Button.class);
		ApiResult result = weixinProxy.createMenu(menuSettings2);
		if (result.getReturnCode().equals("0")) {
			return new Message(Message.Type.success, result.getReturnMsg());
		} else {
			return new Message(Message.Type.error, result.getReturnMsg());
		}
	}

}
