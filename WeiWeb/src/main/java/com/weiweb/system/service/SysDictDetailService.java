package com.weiweb.system.service;

import com.weiweb.system.model.SysDictDetail;

public interface SysDictDetailService {
	
	public void saveDictDetail(SysDictDetail dictDetail) ;

	void updateDictDetail(SysDictDetail dictDetail);
	
	int deleteByPrimaryKey(String id);
}
