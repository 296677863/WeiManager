package com.weiweb.admin.schedule.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.weiweb.admin.schedule.model.ScheduleJobModel;
import com.weiweb.admin.schedule.service.ScheduleJobService;
import com.weiweb.common.controller.BaseController;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.core.shiro.po.Message;

@Controller
@RequestMapping("/schedule")
public class ScheduleJobController extends BaseController{
	
	@Autowired
	private ScheduleJobService scheduleJobService;
	
	@RequestMapping(value = { "list" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index() {
		return "/schedule/list";
	}
	
	@RequestMapping("/listData")
	@ResponseBody
	public Pagination<ScheduleJobModel> listData(ScheduleJobModel user,Integer pageNo, ModelMap map){
		Pagination<ScheduleJobModel> page = scheduleJobService.findByPage(map,pageNo,pageSize);
		return page;
		

	}
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public Message deletescheduleJobById(String ids){
		String[] jobIds = ids.split(",");
		for (String idstr : jobIds) {
			Long[] longid = new Long[1];
			try {
				longid[0] = Long.parseLong(idstr);
			} catch (Exception e) {
				continue;
			}
			scheduleJobService.deleteBatch(longid);
		}
	    return SUCCESS_MESSAGE;
	
	}
	
	@ResponseBody
	@RequestMapping(value = { "pause/{jobIds}" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Message pause(@PathVariable("jobIds") String jobIds) {
		String[] ids = jobIds.split(",");
		for (String idstr : ids) {
			Long[] longid = new Long[1];
			try {
				longid[0] = Long.parseLong(idstr);
			} catch (Exception e) {
				continue;
			}
			scheduleJobService.pause(longid);
		}
		return SUCCESS_MESSAGE;
	}
	
	
	@RequestMapping("/add")
	public String add( ModelMap model){
		return "/schedule/add";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit( @PathVariable("id")String id,ModelMap model){
		if(!StringUtils.isEmpty(id))
			model.addAttribute("bean",scheduleJobService.selectByPrimaryKey(Long.parseLong(id)));
		return "/schedule/edit";
	}
	
	@ResponseBody
	@RequestMapping(value = { "update" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Message update(@RequestBody ScheduleJobModel scheduleJob) {
		// 数据校验
		try {
			scheduleJobService.update(scheduleJob);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR_MESSAGE;
		}
		return SUCCESS_MESSAGE;
	}
	
		
	@RequestMapping("/save")
	@ResponseBody
	public Message save(ScheduleJobModel bean,RedirectAttributes redirectAttributes){
		try{
			if(bean.getJobId()!=null){
				scheduleJobService.update(bean);
			}else{
				scheduleJobService.save(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR_MESSAGE;
		}
		return Message.success(bean);
	}
	
	@RequestMapping("/info/{id}")
	public String info( @PathVariable("id")String id,ModelMap model){
		if(!StringUtils.isEmpty(id))
			model.addAttribute("bean",scheduleJobService.selectByPrimaryKey(Long.parseLong(id)));
		return "/schedulejob/info";
	}
	
	
	@ResponseBody
	@RequestMapping(value = { "run" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Message run(String jobIds) {
		String[] ids = jobIds.split(",");
		for (String idstr : ids) {
			Long[] longid = new Long[1];
			try {
				longid[0] = Long.parseLong(idstr);
			} catch (Exception e) {
				continue;
			}
			scheduleJobService.run(longid);
		}
		return SUCCESS_MESSAGE;
	}
	
	
	@ResponseBody
	@RequestMapping(value = { "resume/{jobIds}" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Message resume(@PathVariable("jobIds") String jobIds) {
		String[] ids = jobIds.split(",");
		for (String idstr : ids) {
			Long[] longid = new Long[1];
			try {
				longid[0] = Long.parseLong(idstr);
			} catch (Exception e) {
				continue;
			}
			scheduleJobService.resume(longid);
		}
		return SUCCESS_MESSAGE;
	}
	
}