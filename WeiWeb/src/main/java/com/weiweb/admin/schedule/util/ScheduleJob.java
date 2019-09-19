package com.weiweb.admin.schedule.util;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.weiweb.admin.schedule.model.ScheduleJobLog;
import com.weiweb.admin.schedule.model.ScheduleJobModel;
import com.weiweb.admin.schedule.service.ScheduleJobLogService;
import com.weiweb.common.utils.DateUtil;
import com.weiweb.common.utils.SpringContextUtil;

import net.sf.json.JSONObject;

/** 定时任务
 * 
 */
public class ScheduleJob extends QuartzJobBean {
	
	private Logger			logger	= LoggerFactory.getLogger(getClass());
	private ExecutorService	service	= Executors.newSingleThreadExecutor();
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JSONObject json = JSONObject.fromObject(context.getMergedJobDataMap().get(ScheduleJobModel.JOB_PARAM_KEY));
		ScheduleJobModel scheduleJob = (ScheduleJobModel) JSONObject.toBean(json, ScheduleJobModel.class);
		// 获取spring bean
		ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtil.getApplicationContext().getBean(ScheduleJobLogService.class);
		// 数据库保存执行记录
		ScheduleJobLog log = new ScheduleJobLog();
		log.setJobId(scheduleJob.getJobId());
		log.setBeanName(scheduleJob.getBeanName());
		log.setMethodName(scheduleJob.getMethodName());
		log.setParams(scheduleJob.getParams());
		log.setCreateTime(DateUtil.getDate());
		log.setError("");
		// 任务开始时间
		long startTime = System.currentTimeMillis();
		try {
			// 执行任务
			logger.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
			ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(), scheduleJob.getParams());
			Future<?> future = service.submit(task);
			future.get();
			// 任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int) times);
			// 任务状态 0：成功 1：失败
			log.setStatus(0);
			logger.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
		} catch (Exception e) {
			logger.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);
			// 任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int) times);
			// 任务状态 0：成功 1：失败
			log.setStatus(1);
			log.setError(StringUtils.substring(e.toString(), 0, 2000));
		} finally {
			try {
				scheduleJobLogService.save(log);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
