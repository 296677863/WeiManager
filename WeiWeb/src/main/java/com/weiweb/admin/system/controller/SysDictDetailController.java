package com.weiweb.admin.system.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weiweb.admin.system.model.SysDictDetail;
import com.weiweb.admin.system.service.SysDictDetailService;
import com.weiweb.common.controller.BaseController;
import com.weiweb.common.utils.UUIDHelper;
import com.weiweb.core.shiro.po.Message;

@Controller
@RequestMapping("/sysdictDeatil")
public class SysDictDetailController  extends BaseController {
	@Autowired
	SysDictDetailService sysDictDetailService;
	
	
	@RequestMapping(value = "/updatedictDetail", method = RequestMethod.POST)
	@ResponseBody
	public Message updateSelectDetail(SysDictDetail dictDetail){
		if(StringUtils.isNotEmpty(dictDetail.getDictType())){
				try {
					if(StringUtils.isEmpty(dictDetail.getDetailId())){
						dictDetail.setDetailId(UUIDHelper.gen());
						sysDictDetailService.saveDictDetail(dictDetail);
					}else{
						sysDictDetailService.updateDictDetail(dictDetail);
					}
				} catch (Exception e) {
					e.printStackTrace();
					return ERROR_MESSAGE;
				}
			
			return  Message.success(dictDetail);
		}else{
			return ERROR_MESSAGE;
		}
	}
	
	@RequestMapping(value="/deleteDictDetail")
	@ResponseBody
	public Message delete(String id){
		if(StringUtils.isNotBlank(id)){
			sysDictDetailService.deleteByPrimaryKey(id);
			return  SUCCESS_MESSAGE;
		}else{
			return ERROR_MESSAGE;
		}
	}
	
	
}