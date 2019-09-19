package com.weiweb.admin.schedule.dao;

import com.weiweb.admin.schedule.model.ScheduleJobLog;


/**
 * 定时任务日志
 * 
 * @author zed
 * @email 296677863@qq.com
 * @date 2017-08-30
 */
public interface ScheduleJobLogMapper {

	int deleteByPrimaryKey(Long id);
	
	int insert(ScheduleJobLog record);
	 
	int insertSelective(ScheduleJobLog record); 
	
	ScheduleJobLog selectByPrimaryKey(Long id);
	
	int updateByPrimaryKeySelective(ScheduleJobLog record);

	int updateByPrimaryKey(ScheduleJobLog record);
	 
	
}
