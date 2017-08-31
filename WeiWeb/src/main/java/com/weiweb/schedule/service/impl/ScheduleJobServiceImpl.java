package com.weiweb.schedule.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weiweb.common.utils.DateUtil;
import com.weiweb.core.mybatis.BaseMybatisDao;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.schedule.dao.ScheduleJobMapper;
import com.weiweb.schedule.model.ScheduleJobModel;
import com.weiweb.schedule.service.ScheduleJobService;
import com.weiweb.schedule.util.Constant.ScheduleStatus;
import com.weiweb.schedule.util.ScheduleUtils;




@Service
public class ScheduleJobServiceImpl extends BaseMybatisDao<ScheduleJobMapper> implements ScheduleJobService {
	@Autowired
	private ScheduleJobMapper scheduleJobMapper;
	
	@Autowired
	private Scheduler		scheduler;

	/** 项目启动时，初始化定时器 
	 * @throws Exception */
	@PostConstruct
	public void init() throws Exception {
		List<ScheduleJobModel> scheduleJobList =scheduleJobMapper.queryListBybeanName(null) ;
		for (ScheduleJobModel scheduleJob : scheduleJobList) {
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
			// 如果不存在，则创建
			if (cronTrigger == null) {
				ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
			}
			else {
				ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
			}
		}
	}
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return scheduleJobMapper.deleteByPrimaryKey(id);
	}
	
	
	@Transactional
	public void deleteBatch(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.deleteScheduleJob(scheduler, jobId);
			scheduleJobMapper.deleteByPrimaryKey(jobId);
		}
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
	@Transactional
	public void save(ScheduleJobModel scheduleJob) throws Exception {
		scheduleJob.setCreateTime(DateUtil.getDate());
		scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
		this.insertSelective(scheduleJob);
		ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
	}
	
	@Transactional
	public void update(ScheduleJobModel scheduleJob) {
		ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
		this.updateByPrimaryKeySelective(scheduleJob);
	}
	
	@Transactional
	@Override
	public void run(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.run(scheduler, queryObject(jobId));
		}
	}
	
	@Transactional
	public void pause(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.pauseJob(scheduler, jobId);
		}
		updateBatch(jobIds, ScheduleStatus.PAUSE.getValue());
	}
	
	
	@Transactional
	public void resume(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.resumeJob(scheduler, jobId);
		}
		updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
	}
	
	private void updateBatch(Long[] jobIds, int status) {
		for(Long jobId : jobIds){
			ScheduleJobModel record=new ScheduleJobModel();
			record.setJobId(jobId);
			record.setStatus(status);
			scheduleJobMapper.updateByPrimaryKeySelective(record);
		}
		
	}

	public ScheduleJobModel queryObject(Long jobId) {
		return scheduleJobMapper.selectByPrimaryKey(jobId);
	}
	
	
}
