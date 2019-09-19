package com.weiweb.admin.schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weiweb.admin.schedule.dao.ScheduleJobLogMapper;
import com.weiweb.admin.schedule.model.ScheduleJobLog;
import com.weiweb.admin.schedule.service.ScheduleJobLogService;

@Service
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService{

	@Autowired
	ScheduleJobLogMapper scheduleJobLogMapper;
	@Override
	public void save(ScheduleJobLog log) {
		scheduleJobLogMapper.insertSelective(log);
	}
	

}
