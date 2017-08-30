package com.weiweb.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weiweb.common.controller.BaseController;

@Controller
@RequestMapping("/schedule")
public class ScheduleJobController extends BaseController{
	
	
	@RequestMapping(value = { "list" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index() {
		return "/schedule/list";
	}
	
	
	
}