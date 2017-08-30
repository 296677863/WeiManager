package com.weiweb.schedule.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.weiweb.common.controller.BaseController;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.core.shiro.po.Message;
import com.weiweb.schedule.model.ScheduleJobModel;
import com.weiweb.schedule.service.ScheduleJobService;

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
	
	@RequestMapping(value="deletescheduleJobById",method=RequestMethod.POST)
	@ResponseBody
	public Message deletescheduleJobById(String ids){
		if( ids!=null){
			return ERROR_MESSAGE;
		}else{
			return SUCCESS_MESSAGE;
		}
	}
	
	@RequestMapping("/add")
	public String add( ModelMap model){
		return "/schedule/add";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit( @PathVariable("id")String id,ModelMap model){
		if(!StringUtils.isEmpty(id))
			model.addAttribute("bean",scheduleJobService.selectByPrimaryKey(Long.parseLong(id)));
		return "/schedulejob/edit";
	}
		
	@RequestMapping("/save")
	@ResponseBody
	public Message save(ScheduleJobModel bean,RedirectAttributes redirectAttributes){
		try{
			scheduleJobService.insertSelective(bean);
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
	@RequestMapping(value = { "run.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public void run(String jobIds) {
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
	}
	
}