package com.weiweb.weixin.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.weiweb.common.controller.BaseController;
import com.weiweb.common.utils.StringUtils;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.core.shiro.po.Message;
import com.weiweb.weixin.model.WxImgArticle;
import com.weiweb.weixin.service.WxImgArticleService;



/**
 * 
 * 
 * @author zed
 * @email 296677863@qq.com
 * @date 2017-09-01
 */
@Controller
@RequestMapping("/base/wximgarticle")
public class WxImgArticleController extends BaseController{
	@Autowired
	private WxImgArticleService wxImgArticleService;
	
	@RequestMapping(value="list")
	public ModelAndView list(ModelMap map,Integer pageNo,String findContent){
		return new ModelAndView("weixin/wxImgArticle/list");
	}
	
	@RequestMapping("/listData")
	@ResponseBody
	public Pagination<WxImgArticle> listData(WxImgArticle user,Integer pageNo, ModelMap map){
	
		Pagination<WxImgArticle> page = wxImgArticleService.findPage(map,pageNo,pageSize);
		return page;
		

	}
	
	@RequestMapping(value="deletewxImgArticleById",method=RequestMethod.POST)
	@ResponseBody
	public Message deletewxImgArticleByIds(String ids){
		String[] arryids = ids.split(",");
		if( wxImgArticleService.deletewxImgArticleByIds(arryids)){
			return ERROR_MESSAGE;
		}else{
			return SUCCESS_MESSAGE;
		}
	}
	
	@RequestMapping("/add")
	public String add( ModelMap model){
		return "weixin/wxImgArticle/add";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit( @PathVariable("id")String id,ModelMap model){
		if(!StringUtils.isEmpty(id))
			model.addAttribute("bean",wxImgArticleService.selectByPrimaryKey(id));
		return "weixin/wxImgArticle/edit";
	}
		
	@RequestMapping("/save")
	@ResponseBody
	public Message save(WxImgArticle bean,RedirectAttributes redirectAttributes){
		try{
			wxImgArticleService.insert(bean);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR_MESSAGE;
		}
		return Message.success(bean);
	}
	
	@RequestMapping("/info/{id}")
	public String info( @PathVariable("id")String id,ModelMap model){
		if(!StringUtils.isEmpty(id))
			model.addAttribute("bean",wxImgArticleService.selectByPrimaryKey(id));
		return "weixin/wxImgArticle/info";
	}
	
	
	
	
	
}
