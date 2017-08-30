package com.weiweb.schedule.service;

import com.weiweb.schedule.model.ScheduleJobModel;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.ui.ModelMap;

import com.weiweb.core.mybatis.page.Pagination;


/**
 * 定时任务
 * 
 * @author zed
 * @email 296677863@qq.com
 * @date 2017-08-30
 */
public interface ScheduleJobService {

    Pagination<ScheduleJobModel> findByPage(Map<String, Object> resultMap,
			Integer pageNo, Integer pageSize);
	
	
	int deleteByPrimaryKey(Long id);
	
	
	int insert(ScheduleJobModel record);
	
	int insertSelective(ScheduleJobModel record);
		
	ScheduleJobModel selectByPrimaryKey(Long id);
	
	
	int updateByPrimaryKeySelective(ScheduleJobModel  record);

    int updateByPrimaryKey(ScheduleJobModel  record);


	void run(Long[] longid);
	
	 
}
