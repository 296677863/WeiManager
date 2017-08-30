package com.weiweb.schedule.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.weiweb.core.mybatis.BaseMybatisDao;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.schedule.dao.ScheduleJobMapper;
import com.weiweb.schedule.model.ScheduleJobModel;
import com.weiweb.schedule.service.ScheduleJobService;




@Service
public class ScheduleJobServiceImpl extends BaseMybatisDao<ScheduleJobMapper> implements ScheduleJobService {
	@Autowired
	private ScheduleJobMapper scheduleJobMapper;
	
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return scheduleJobMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int insert(ScheduleJobModel record) {
		return scheduleJobMapper.insert(record);
	}
	
	@Override
	public int insertSelective(ScheduleJobModel record) {
		return scheduleJobMapper.insertSelective(record);
	}
	
	@Override
	public ScheduleJobModel selectByPrimaryKey(Long id) {
		return scheduleJobMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(ScheduleJobModel record) {
		return scheduleJobMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ScheduleJobModel record) {
		return scheduleJobMapper.updateByPrimaryKeySelective(record);
	}

	
	@Override
	public Pagination<ScheduleJobModel> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
		return super.findPage(resultMap, pageNo, pageSize);
	}

	@Override
	public void run(Long[] longid) {
		
	}

	
	
}
