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
import com.weiweb.common.controller.BaseController;
import com.weiweb.common.utils.OperType;
import com.weiweb.core.annotation.UserLogAnno;
import com.weiweb.core.shiro.po.Message;

@Controller
@RequestMapping("/base/wxmenu")
public class WxMenuController extends BaseController {
	 private static Logger logger = LoggerFactory.getLogger(WxMenuController.class);


}
