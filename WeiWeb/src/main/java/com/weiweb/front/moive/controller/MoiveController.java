package com.weiweb.front.moive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weiweb.common.controller.BaseController;

@Controller
@RequestMapping("/f/moive")
public class MoiveController extends BaseController{
	@RequestMapping(value="index")
	public ModelAndView index(){
		return new ModelAndView("front/moive/index");
	}
}
