package com.weiweb.admin.movie.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.weiweb.admin.movie.domain.Movie;
import com.weiweb.admin.movie.service.MovieService;
import com.weiweb.common.controller.BaseController;
import com.weiweb.common.utils.StringUtils;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.core.shiro.po.Message;



/**
 * 
 * 
 * @author zed
 * @email 296677863@qq.com
 * @date 2019-08-13
 */
@Controller
@RequestMapping("movie")
public class MovieController extends BaseController{
	@Autowired
	private MovieService movieService;
	
	@RequestMapping(value="list")
	public ModelAndView list(ModelMap map,Integer pageNo,String findContent){
		return new ModelAndView("movie/list");
	}
	
	@RequestMapping("/listData")
	@ResponseBody
	public Pagination<Movie> listData(Movie movie,Integer pageNo, ModelMap map){
		Pagination<Movie> page = movieService.findPage(map, pageNo, pageSize);
		return page;
	}
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public Message deletemovieByIds(String ids){
		String[] arryids = ids.split(",");
		if( movieService.deleteMovieByIds(arryids)){
			return ERROR_MESSAGE;
		}else{
			return SUCCESS_MESSAGE;
		}
	}
	
	@RequestMapping("/add")
	public String add( ModelMap model){
		return "/movie/add";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit( @PathVariable("id")String id,ModelMap model){
		if(!StringUtils.isEmpty(id))
			model.addAttribute("bean",movieService.selectByPrimaryKey(id));
		return "/movie/edit";
	}
		
	@RequestMapping("/save")
	@ResponseBody
	public Message save(Movie bean,RedirectAttributes redirectAttributes){
		try{
			movieService.insert(bean);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR_MESSAGE;
		}
		return Message.success(bean);
	}
	
	@RequestMapping("/info/{id}")
	public String info( @PathVariable("id")String id,ModelMap model){
		if(!StringUtils.isEmpty(id))
			model.addAttribute("bean",movieService.selectByPrimaryKey(id));
		return "/movie/info";
	}
	
	
	
	
	
}
