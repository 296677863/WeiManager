package com.weiweb.gen.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.gen.model.TableEntity;
import com.weiweb.gen.service.SysGeneratorService;

@Controller
@RequestMapping("/sysgenerator")
public class SysGeneratorController {
	@Autowired
	private SysGeneratorService sysGeneratorService;

	@RequestMapping("/index")
	public String index(){
		return "/sysgenerator/index";
	}
	@RequestMapping(value="listData")
	@ResponseBody
	public Pagination<TableEntity> listData(String findContent,ModelMap modelMap,Integer pageNo){
		return  sysGeneratorService.list(findContent, modelMap, pageNo);
	}
	
	@RequestMapping(value = { "code" }, method = { RequestMethod.GET, RequestMethod.POST })
	public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] tableNames = new String[] {};
		// 获取表名，不进行xss过滤
		String tables = request.getParameter("tables");
		tableNames = tables.split(",");
		byte[] data = sysGeneratorService.generatorCode(tableNames);
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"gencode.zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");
		IOUtils.write(data, response.getOutputStream());
	}
	
}
