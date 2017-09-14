package com.weiweb.schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weiweb.schedule.dao.ScheduleJobLogMapper;
import com.weiweb.schedule.model.ScheduleJobLog;
import com.weiweb.schedule.service.ScheduleJobLogService;

@Service
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService{

	@Autowired
	ScheduleJobLogMapper scheduleJobLogMapper;
	@Override
	public void save(ScheduleJobLog log) {
		scheduleJobLogMapper.insertSelective(log);
	}
	

}
