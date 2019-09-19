package com.weiweb.admin.system.service;

import java.util.List;

import com.weiweb.admin.system.bo.Menu;

public interface MenuService {

	List<Menu> findLevelMenu(int i);

}
