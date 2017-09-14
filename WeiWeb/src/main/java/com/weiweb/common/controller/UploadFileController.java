package com.weiweb.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.weiweb.common.model.UploadFile;
import com.weiweb.common.service.UploadFileService;
import com.weiweb.common.utils.ToolKit;
import com.weiweb.core.shiro.po.Message;

@Controller
@RequestMapping("/base/uploadFile")
public class UploadFileController extends BaseController {
	 private static Logger logger = LoggerFactory.getLogger(UploadFileController.class);
	
	 @Autowired
	 UploadFileService service;
	 /**
	 * 图片上传
	 * 
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping("/imageUpload")
	@ResponseBody
	public void imageUpload(MultipartFile file, String entityType, HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException, IOException {
		String jsonString = null;
		if (file == null || ToolKit.isnull(entityType)) {
			Message message = Message.error(message("uploadFile.error.delete.notBlankEntityType"));
			jsonString = JSON.toJSONString(message);
		} else {
			File localFile = service.upload(file, entityType);
			UploadFile uploadFile = service.fileToTable(localFile, entityType, file.getOriginalFilename(), request);
			uploadFile.setFilePath(uploadFile.getFilePath().replaceAll("\\\\","/"));
			jsonString = JSON.toJSONString(uploadFile);
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(jsonString);
			logger.debug("return json data[" + jsonString + "]");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 删除图片，从服务器、数据库中删除
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 */
	@RequestMapping("/delUpload")
	@ResponseBody
	public Message delUpload(String filePath) {
		if (ToolKit.isnull(filePath)) {
			return Message.error(message("uploadFile.error.delete.notPath"));
		}
		filePath = filePath.replaceFirst("/upload","");
		service.delFile(filePath);
		return Message.success("success");
	}

	/**
	 * demo页面
	 * 
	 * @return
	 */
	@RequestMapping("/imageDemo")
	public String imageDemo() {
		return "/base/uploadFile/imageDemo";
	}

	/**
	 * 添加附件测试代码
	 * 
	 * @param imsgJson
	 *            附件json字符串
	 * @param ref_id
	 *            关联主键id
	 * @return
	 */
	@RequestMapping("/saveImg")
	@ResponseBody
	public String saveImg(String imsgJson, String ref_id) {
		try {
			service.updateInfo(imsgJson, ref_id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 根据关联主键id和业务类型加载附件信息
	 * 
	 * @param relationId
	 *            关联主键id
	 * @param entityType
	 *            关联业务类型
	 * @return
	 */
	@RequestMapping("/loadFile")
	@ResponseBody
	public List<UploadFile> loadFile(String relationId, String entityType) {
		UploadFile up = new UploadFile();
		up.setRelationId(relationId);
		up.setEntityType(entityType);
		return service.findUploadFile(up);
	}

	/**
	 * 图片上传并保存
	 * 
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping("/saveImageUpload")
	@ResponseBody
	public UploadFile saveImageUpload(MultipartFile file, String entityType) throws IllegalStateException, IOException {
		if (file == null || ToolKit.isnull(entityType)) {
			return null;
		}
		File localFile = service.upload(file, entityType);
		return service.saveFileToTable(localFile, entityType, file.getOriginalFilename());
	}

	// 根据请求的路径中的参数id,从本地磁盘中读取图片，picUrl是从配置文件中读取出来的
	@RequestMapping("/tofindPic/{id}")
	public String picToJSP(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		File file = service.getFileById(id);
		if (file.exists()) { // 如果文件存在
			response.setContentType("image/jpeg"); // 设置返回内容格式
			InputStream in = new FileInputStream(file); // 用该文件创建一个输入流
			OutputStream os = response.getOutputStream(); // 创建输出流
			byte[] b = new byte[1024];
			while (in.read(b) != -1) {
				os.write(b);
			}
			in.close();
			os.flush();
			os.close();
		}
		return null;
	}

	@RequestMapping("/deleteUpload")
	@ResponseBody
	public Message delUploadById(@RequestParam(value = "ids[]") String[] ids) {
		if (ArrayUtils.isEmpty(ids)) {
			return Message.error("id is null");
		}
		service.deleteFileByIds(ids);
		return Message.success("success");
	}

}
