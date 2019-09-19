package com.weiweb.admin.schedule.service;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.weiweb.admin.schedule.model.ScheduleJobModel;
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


    @Transactional
	void save(ScheduleJobModel scheduleJob) throws Exception;
	
	@Transactional
	void update(ScheduleJobModel scheduleJob);
	
	@Transactional
	void deleteBatch(Long[] jobIds);
	
	
	@Transactional
	void run(Long... jobIds);
	
	@Transactional
	void pause(Long... jobIds);
	
	@Transactional
	void resume(Long... jobIds);
	
	 
}
