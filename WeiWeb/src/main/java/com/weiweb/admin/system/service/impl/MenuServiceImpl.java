package com.weiweb.admin.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weiweb.admin.system.bo.Menu;
import com.weiweb.admin.system.service.MenuService;
import com.weiweb.common.dao.UPermissionMapper;

@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	UPermissionMapper upermissionMapper;

	@Override
	public List<Menu> findLevelMenu(int i) {
		List<Menu> menus=upermissionMapper.findLevelMenu(i);
		return menus;
	}

}
