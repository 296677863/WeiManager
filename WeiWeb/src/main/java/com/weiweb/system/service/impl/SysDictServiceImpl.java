package com.weiweb.system.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.sun.org.apache.commons.collections.CollectionUtils;
import com.weiweb.core.mybatis.BaseMybatisDao;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.system.dao.SysDictDetailMapper;
import com.weiweb.system.dao.SysDictMapper;
import com.weiweb.system.model.SysDict;
import com.weiweb.system.model.SysDictDetail;
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
	public void deleteIds(String[] ids) {
		for (String id : ids) {
			SysDict temp=sysDictMapper.selectByPrimaryKey(id);
			String tempDictType=temp.getDictType();
			List<SysDictDetail> sysDictDetails=sysDictDetailMapper.selectByDictType(tempDictType);
			if(sysDictDetails!=null&&sysDictDetails.size()!=0){
				for(SysDictDetail sysDictDetail:sysDictDetails){
					sysDictDetailMapper.deleteByPrimaryKey(sysDictDetail.getDetailId());
				}
			}
			this.deleteByPrimaryKey(id);
		}
	}
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return sysDictMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public void updateDict(SysDict sysDict) {
		SysDict temp=sysDictMapper.selectByPrimaryKey(sysDict.getDictId());
		String tempDictType=temp.getDictType();
		
		if(!sysDict.getDictType().equals(tempDictType)){
			List<SysDictDetail> sysDictDetails=sysDictDetailMapper.selectByDictType(tempDictType);
			for(SysDictDetail sysDictDetail:sysDictDetails){
				sysDictDetail.setDictType(sysDict.getDictType());
				sysDictDetailMapper.updateByPrimaryKeySelective(sysDictDetail);
			}
			
		}
		sysDictMapper.updateByPrimaryKeySelective(sysDict);
	}

	@Override
	public void saveDict(SysDict sysDict) {
		sysDictMapper.insert(sysDict);
	}


}
