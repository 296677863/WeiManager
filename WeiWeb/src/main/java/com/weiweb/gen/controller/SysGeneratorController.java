package com.weiweb.gen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weiweb.gen.service.SysGeneratorService;

@Controller
@RequestMapping("/sysgenerator")
public class SysGeneratorController {
	@Autowired
	private SysGeneratorService sysGeneratorService;

	@RequestMapping("/index")
	public String index(){
		return "/sysgenerator/index";
	}
	
}
