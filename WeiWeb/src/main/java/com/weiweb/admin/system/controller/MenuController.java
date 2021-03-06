package com.weiweb.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weiweb.admin.system.bo.Menu;
import com.weiweb.admin.system.service.MenuService;
import com.weiweb.common.controller.BaseController;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
	
	@Autowired
	MenuService menuService;
	
	/**
	 * 列表
	 * 
	 * @param menu
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Menu menu, ModelMap model) {
		model.addAttribute("parent", menuService.findLevelMenu(1));
		model.addAttribute("sub", menuService.findLevelMenu(2));
		return "/menu/menu_list";
	}

}
