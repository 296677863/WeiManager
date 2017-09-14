package com.weiweb.common.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.weiweb.common.model.UploadFile;
import com.weiweb.common.service.UploadFileService;
import com.weiweb.core.statics.Constant;

public class UploadFileServiceImpl implements UploadFileService {

	@Override
	public File upload(MultipartFile file, String type) throws IllegalStateException, IOException {
		String fileName = createEmptyFile(file.getOriginalFilename(), type);
		File uploadFile = new File(fileName);
		file.transferTo(uploadFile); // 写入
		return uploadFile;
	}

	@Override
	public List<UploadFile> entityFiles(String id) {
		return null;
	}

	@Override
	public String rename() {
		return null;
	}

	@Override
	public UploadFile fileToTable(File file, String type, String realFilename, HttpServletRequest request) {
		return null;
	}

	@Override
	public UploadFile saveFileToTable(File file, String type, String realFilename) {
		return null;
	}

	@Override
	public void deleteFileFormServer(String filePath) {
		
	}

	@Override
	public void deleteFileFormTable(String filePath) {
		
	}

	@Override
	public void deleteFileByRelationIds(String[] ids) {
		
	}

	@Override
	public void updateInfo(String imsgJson, String relationId) throws Exception {
		
	}

	@Override
	public String upload(String base64, String type) throws IllegalStateException, IOException {
		return null;
	}

	@Override
	public File getFile(String filename) {
		return null;
	}

	@Override
	public File getFileById(String id) {
		return null;
	}

	@Override
	public void deleteFileByIds(String[] ids) {
		
	}

	/**
	 * 上传附件时，根据附件的实际文件名按照时间、随机数产生存储文件名，避免重复
	 * 
	 * @param fileName
	 *            实际文件名
	 * @param type
	 *            附件业务类型
	 * @return
	 */
	@Override
	public synchronized String createEmptyFile(String fileName, String type) {
		String root = SystemConfigCache.findValueByName(Constant.UPLOADFILE_BASEFILEPATH);
		createFolder(root);
		root += "\\" + type;
		createFolder(root);
		root += "\\" + TimeUtils.dateToString(new Date(), "yyyyMM");
		createFolder(root);
		Random random = new Random();
		root += "\\" + type + "_" + new Date().getTime()
				+ (random.nextInt(100))
				+ fileName.substring(fileName.indexOf("."));
		return root;

	}

	@Override
	public void delFile(String filePath) {
		
	}

	@Override
	public List<UploadFile> findUploadFile(UploadFile up) {
		return null;
	}

}
