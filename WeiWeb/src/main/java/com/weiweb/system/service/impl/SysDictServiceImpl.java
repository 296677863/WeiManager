package com.weiweb.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.weiweb.core.mybatis.BaseMybatisDao;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.system.dao.SysDictDetailMapper;
import com.weiweb.system.dao.SysDictMapper;
import com.weiweb.system.model.SysDict;
import com.weiweb.system.service.SysDictService;

@Service
public class SysDictServiceImpl extends BaseMybatisDao<SysDictMapper>  implements SysDictService{
	@Autowired
	SysDictMapper sysDictMapper;
	@Autowired
	SysDictDetailMapper sysDictDetailMapper;

	@Override
	public Pagination<SysDict> findByPage(ModelMap map, Integer pageNo, int pageSize) {
		return super.findPage(map, pageNo, pageSize);
	}

	@Override
	public List<SysDict> findSysDictByType(String dictType) {
		return sysDictMapper.findSysDictByType(dictType);
	}

	@Override
	public void deleteUserById(String[] ids) {
		for (String id : ids) {
			this.deleteByPrimaryKey(id);
		}
	}
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return sysDictMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateDict(SysDict sysDict) {
		
	}

	@Override
	public void saveDict(SysDict sysDict) {
		
	}


}
