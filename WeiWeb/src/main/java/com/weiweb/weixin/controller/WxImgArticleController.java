package com.weiweb.weixin.controller;



import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
import com.weiweb.common.utils.UUIDHelper;
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
			model.addAttribute("news",wxImgArticleService.selectByPrimaryKey(id));
		return "weixin/wxImgArticle/add";
	}
		
	@RequestMapping("/save")
	@ResponseBody
	public Message save(WxImgArticle article,RedirectAttributes redirectAttributes){
		try {
			if (article.getId() != null) {
				wxImgArticleService.updateByPrimaryKeySelective(article);
			} else {
				if (StringUtils.isNotBlank(article.getTitle())) {
					article.setCreateDate(new Date());
					article.setId(UUIDHelper.gen());
					wxImgArticleService.insert(article);
				} else {
					return Message.error(message("news.error.notTitle"));
				}

			}
		} catch (Exception e) {
			return Message.error(e.getMessage());
		}
		return Message.success(article);
		
		
	}
	
	
	/****
	 * 预览页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/view/{id}")
	public String view(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("news", wxImgArticleService.selectByPrimaryKey(id));
		return "weixin/wxImgArticle/view";
	}
	
	/****
	 * 预览页面
	 * @return
	 */
	@RequestMapping("/exhibitionsave")
	@ResponseBody
	public Message exhibitionsave(WxImgArticle article, HttpServletRequest request) {
		try {
			article.setCreateDate(new Date());
			request.getSession().setAttribute("instantNews",article);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		return SUCCESS_MESSAGE;
	}
	/****
	 * 预览页面
	 * @return
	 */
	@RequestMapping("/viewPage")
	public String viewPage(HttpServletRequest request,ModelMap model) {
		WxImgArticle article  = (WxImgArticle)request.getSession().getAttribute("instantNews");
		if(article == null){
			return "redirect:/resource_not_found";
		}
		model.addAttribute("news", article);
		return "weixin/wxImgArticle/view";
	}

	
	
	/***
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteCover")
	@ResponseBody
	public Message deleteCover(String id, ModelMap model) {
		if (StringUtils.isNotBlank(id)) {
			try {
				WxImgArticle wxImgArticle = wxImgArticleService.selectByPrimaryKey(id);
				wxImgArticle.setCover("");
				wxImgArticleService.updateByPrimaryKey(wxImgArticle);
			} catch (Exception e) {
				e.printStackTrace();
				return Message.error(e.getMessage());
			}
		}
		return SUCCESS_MESSAGE;
	}
	
	
	
	
}
