package com.weiweb.admin.schedule.dao;

import java.util.List;

import com.weiweb.admin.schedule.model.ScheduleJobModel;


/**
 * 定时任务
 * 
 * @author zed
 * @email 296677863@qq.com
 * @date 2017-08-30
 */
public interface ScheduleJobMapper {

	int deleteByPrimaryKey(Long id);
	
	int insert(ScheduleJobModel record);
	 
	int insertSelective(ScheduleJobModel record); 
	
	ScheduleJobModel selectByPrimaryKey(Long id);
	
	int updateByPrimaryKeySelective(ScheduleJobModel record);

	int updateByPrimaryKey(ScheduleJobModel record);

	List<ScheduleJobModel> queryListBybeanName(String  beanName);

	ScheduleJobModel queryObject(Long jobId);
	 
	
}
