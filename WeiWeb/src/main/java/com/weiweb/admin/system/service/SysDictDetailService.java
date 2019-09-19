package com.weiweb.admin.system.service;

import com.weiweb.admin.system.model.SysDictDetail;

public interface SysDictDetailService {
	
	public void saveDictDetail(SysDictDetail dictDetail) ;

	void updateDictDetail(SysDictDetail dictDetail);
	
	int deleteByPrimaryKey(String id);
}
