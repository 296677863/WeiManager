package com.weiweb.common.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.weiweb.common.model.UploadFile;

public interface UploadFileService {
	/**
	 * 文件上传到文件夹
	 * @param file
	 * @return 返回文件名
	 */
	public File upload(MultipartFile file, String type) throws IllegalStateException, IOException ;	
	
	/**
	 * 获得entity关联的文件集合
	 * @param id
	 * @return
	 */
	public List<UploadFile> entityFiles(String id);
	/**
	 * 改名
	 * @return
	 */
	public String rename();
	
	
	/**
	 * 文件转换成数据库对象
	 * @param file
	 * @return
	 */
	public UploadFile fileToTable(File file,String type,String realFilename,HttpServletRequest request);
	
	/**
	 * 保存文件到数据库
	 * @param file
	 * @return
	 */
	public UploadFile saveFileToTable(File file,String type,String realFilename);
	
	/**
	 * 从服务器上删除附件信息
	 * @param filePath
	 */
	public void deleteFileFormServer(String filePath);
	/**
	 * 从数据库中删除附件信息
	 * @param filePath
	 */
	public void deleteFileFormTable(String filePath);
	
	/**
	 * 
	 * @param ids
	 */
	public void deleteFileByRelationIds(String[] ids);
	
	/**
	 * 根据页面传入的附件json字符串以及关联主键id进行保存或更新附件信息
	 * @param imsgJson 附件json字符串
	 * @param relationId
	 * @throws Exception
	 */
	public void updateInfo(String imsgJson,String relationId) throws Exception;
	
	/**
	 * base64图片上传到文件夹
	 * @param base64
	 * @param type
	 * @return
	 */
	public  String upload(String base64, String type) throws IllegalStateException, IOException;
	
	/**
	 * 文件返回
	 * @param filename
	 * @return
	 */
	public File getFile(String filename);
	
	/**
	 * 文件返回
	 * @param filename
	 * @return
	 */
	public File getFileById(String id);

	
	/**
	 * 删除文件根据id
	 * @param filename
	 * @return
	 */
	void deleteFileByIds(String[] ids);
	


	/***
	 * 创建文件路径
	 * @param fileName
	 * @param type
     * @return
     */
	public String createEmptyFile(String fileName, String type);

	/**
	 * 删除文件
	 * @param filePath
	 */
	public void delFile(String filePath);

	public List<UploadFile> findUploadFile(UploadFile up);
}
