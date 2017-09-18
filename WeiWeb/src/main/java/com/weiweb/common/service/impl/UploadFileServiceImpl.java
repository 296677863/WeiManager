package com.weiweb.common.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weiweb.common.dao.UploadFileMapper;
import com.weiweb.common.model.UploadFile;
import com.weiweb.common.service.UploadFileService;
import com.weiweb.common.utils.DateUtil;
import com.weiweb.common.utils.JsonUtils;
import com.weiweb.common.utils.ToolKit;
import com.weiweb.common.utils.UUIDHelper;
import com.weiweb.common.utils.VideoThumbTaker;
import com.weiweb.core.shiro.cache.impl.SystemConfigCache;
import com.weiweb.core.statics.Constant;

import net.coobird.thumbnailator.Thumbnails;


@Service
public class UploadFileServiceImpl implements UploadFileService {

	@Autowired
	UploadFileMapper uploadFileMapper;
	
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
	@Transactional
	public UploadFile fileToTable(File file, String type, String realFilename, HttpServletRequest request) {
		String root = SystemConfigCache.findValueByName(Constant.UPLOADFILE_BASEFILEPATH);
		
		UploadFile uploadfile = new UploadFile();
		uploadfile.setEntityType(type);
		uploadfile.setFileType(realFilename.substring(realFilename
				.lastIndexOf(".") + 1));
		uploadfile.setFileName(file.getName());
		uploadfile.setFilePath(file.getPath().replace(root, ""));
		uploadfile.setFileSize(Integer.valueOf(String.valueOf(file.length())));
		uploadfile.setCnName(realFilename);
		
		//生成缩略图
		try {
			if(realFilename.substring(realFilename
					.lastIndexOf(".") + 1).equalsIgnoreCase("jpg")
					||
					realFilename.substring(realFilename
							.lastIndexOf(".") + 1).equalsIgnoreCase("gif")
					|| realFilename.substring(realFilename
							.lastIndexOf(".") + 1).equalsIgnoreCase("jpeg")
					|| realFilename.substring(realFilename
							.lastIndexOf(".") + 1).equalsIgnoreCase("bmp")
					|| realFilename.substring(realFilename
							.lastIndexOf(".") + 1).equalsIgnoreCase("png")){
				
				String tempFilePath = file.getPath();
				String path1 = tempFilePath.substring(0, tempFilePath.lastIndexOf("."));
				String path2 = tempFilePath.substring( tempFilePath.lastIndexOf("."),tempFilePath.length());
				Thumbnails.of(file.getPath()).size(80, 80).toFile(path1+"_min"+path2);
				String minPath = path1+"_min"+path2;
				
				uploadfile.setMinPath(minPath.replace(root, ""));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String fileType = file.getPath().substring(file.getPath()
				.lastIndexOf(".") + 1);
		
		if("mp4".equalsIgnoreCase(fileType) || "WebM".equalsIgnoreCase(fileType) || "Ogg".equalsIgnoreCase(fileType)){
			if(request != null){
				String ffmpegPath = request.getServletContext().getRealPath("/tools/ffmpeg.exe");
				String jpgPath = file.getPath().replace(fileType, "jpg");
				executeCodecs(ffmpegPath,file.getPath(),jpgPath);
				uploadfile.setCoverPath(jpgPath.replace(root, "").replaceAll("\\\\","/"));
			}

		}
		uploadfile.setId(UUIDHelper.gen());
		uploadFileMapper.insert(uploadfile);
		return uploadfile;
	}
	
	/***
	 * 获取视频缩略图
	 * @param ffmpegPath
	 * @param upFilePath
	 * @param mediaPicPath
	 */
	public void executeCodecs(String ffmpegPath,String upFilePath,String mediaPicPath){
		VideoThumbTaker videoThumbTaker = new VideoThumbTaker(ffmpegPath);
		try {
			videoThumbTaker.getThumb(upFilePath, mediaPicPath, 800, 600, 0, 0, 1);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
//		 List<String> cutpic = new ArrayList<String>();
//	        cutpic.add(ffmpegPath);
//	        cutpic.add("-i");
//	        cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
//	        cutpic.add("-y");
//	        cutpic.add("-f");
//	        cutpic.add("image2");
//	        cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
//	        cutpic.add("1"); // 添加起始时间为第17秒
//	        cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
//	        cutpic.add("0.001"); // 添加持续时间为1毫秒
////	        cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
////	        cutpic.add("608*342"); // 添加截取的图片大小为350*240
//	        cutpic.add(mediaPicPath); // 添加截取的图片的保存路径
//            // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
//            //因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
//            try {
//            	  ProcessBuilder builder = new ProcessBuilder();
//      	          builder.command(cutpic);
//                  builder.redirectErrorStream(true);
//				  Process process = builder.start();
//				  process.waitFor();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	}


	@Override
	public UploadFile saveFileToTable(File file, String type, String realFilename) {
		UploadFile uploadFile =  fileToTable(file, type, realFilename,null);
		return uploadFile;
	}

	@Override
	public void deleteFileFormServer(String filePath) {
		// 删除本地数据
		String root = SystemConfigCache.findValueByName(Constant.UPLOADFILE_BASEFILEPATH);
		File file = new File(root + filePath);
		if (null != file && file.exists()) {
			file.delete();
		}
	}
	
	@Override
	public void deleteFileFormTable(String filePath) {
		// 删除数据库的数据
		List<UploadFile> oldList =uploadFileMapper.selectByFilePath(filePath); 
		for (UploadFile uploadFile : oldList) {
			uploadFileMapper.deleteByPrimaryKey(uploadFile.getId());
		}
	}

	@Override
	public void deleteFileByRelationIds(String[] ids) {
		for (String relationId : ids) {
			List<UploadFile> oldList = uploadFileMapper.selectByRelationId(relationId);
			for (UploadFile uploadFile : oldList) {
				deleteFileFormServer(uploadFile.getFilePath());
				deleteFileFormTable(uploadFile.getFilePath());
			}
		}
	}

	@Override
	public void updateInfo(String imsgJson, String relationId) throws Exception {
		List<UploadFile> formList = getListForJson(imsgJson);
		// 从数据库、服务器上删除页面上已删除的信息
		deletFile(formList, relationId);
		// 保存或更新  
		if(!CollectionUtils.isEmpty(formList)){
			saveOrUpdate(formList, relationId);
		}
	}
	
	/**
	 * 保存或更新附件信息
	 * @param formList 所有附件信息
	 * @param relationId 关联主键id
	 */
	private void saveOrUpdate(List<UploadFile> formList, String relationId) {
		if (!CollectionUtils.isEmpty(formList)) {
			for (UploadFile uploadFile : formList) {
				if (ToolKit.isnull(uploadFile.getId())) {
					uploadFile.setRelationId(relationId);
					uploadFile.setCreateDate(new Date());
					uploadFile.setModifyDate(new Date());
					uploadFileMapper.insertSelective(uploadFile);
				} else {
					uploadFile.setModifyDate(new Date());
					uploadFileMapper.updateByPrimaryKey(uploadFile);
				}
			}
		}
	}
	
	/**
	 * 编辑信息时，先在服务器、数据库中删除通过页面删除的附件信息
	 * 
	 * @param formList
	 *            页面上所有附件信息
	 * @param relationId
	 *            关联主键id
	 */
	private void deletFile(List<UploadFile> formList, String relationId) {

		List<UploadFile> updateList = getUpdateList(formList);
		List<UploadFile> oldList = uploadFileMapper.selectByRelationId(relationId);

		List<UploadFile> delList = getDeleteList(oldList, updateList);
		if(!CollectionUtils.isEmpty(delList)){
			for (UploadFile uploadFile : delList) {
				// 删除数据库的数据
				deleteFileFormTable(uploadFile.getFilePath());
				// 删除本地数据
				deleteFileFormServer(uploadFile.getFilePath());
			}
		}
	}
	
	/**
	 * 根据页面上所有附件信息得到 只需要更新的信息
	 * 
	 * @param uploadList
	 *            页面所有附件信息
	 * @return
	 */
	private List<UploadFile> getUpdateList(List<UploadFile> uploadList) {
		List<UploadFile> updateList = new ArrayList<UploadFile>();
		if(!CollectionUtils.isEmpty(uploadList)){
			for (UploadFile uploadFile : uploadList) {
				if (!ToolKit.isnull(uploadFile.getId())) {
					updateList.add(uploadFile);
				}
			}
		}
		return updateList;
	}
	/**
	 * 通过附件json集合字符串转化为List
	 * 
	 * @param imsgJson
	 *            json集合字符串
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private List<UploadFile> getListForJson(String imsgJson)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper obj = new ObjectMapper();
		List<Map<String, Object>> list = obj.readValue(imsgJson, List.class);
		List<UploadFile> result = new ArrayList<UploadFile>();
		if (!CollectionUtils.isEmpty(list)) {
			for (Map<String, Object> map : list) {
				result.add(JsonUtils.toObject(JsonUtils.toJson(map),
						UploadFile.class));
			}
		}
		return result;
	}
	
	/**
	 * 根据数据库已有数据和页面上保留有主键的附件信息比较得到已删除的信息
	 * 
	 * @param oldList
	 *            数据库已有信息
	 * @param updateList
	 *            页面上有主键的id信息
	 * @return
	 */
	private List<UploadFile> getDeleteList(List<UploadFile> oldList,
			List<UploadFile> updateList) {
		List<UploadFile> delList = new ArrayList<UploadFile>();
		if(!CollectionUtils.isEmpty(oldList)){
			for (UploadFile old : oldList) {
				boolean isHas = false;
				for (UploadFile update : updateList) {
					if (old.getId().equals(update.getId())) {
						isHas = true;
						break;
					}
				}
				//
				if (!isHas) {
					delList.add(old);
				}
			}
		}
		return delList;
	}
	@Override
	public String upload(String base64, String type) throws IllegalStateException, IOException {
		String root = SystemConfigCache.findValueByName(Constant.UPLOADFILE_BASEFILEPATH);

		String fileName = createEmptyFile(".jpg", type);
		try {
			byte[] b = Base64Utils.decodeFromString(base64);
				for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
				b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(fileName);
			out.write(b);
			out.flush();
			out.close();
			} catch (Exception e) {
				
				return null;
			}
		
		return fileName.substring(root.length());
	}

	@Override
	public File getFile(String filename) {
		String root = SystemConfigCache.findValueByName(Constant.UPLOADFILE_BASEFILEPATH);
		File file=new File(root+filename);
		if(file.exists()){
			return file;
		}
		return null;
	}

	@Override
	public File getFileById(String id) {
		UploadFile  uploadFile =  uploadFileMapper.selectByPrimaryKey(id);
		String root = SystemConfigCache.findValueByName(Constant.UPLOADFILE_BASEFILEPATH);
		File file=new File(root+uploadFile.getFilePath());
		if(file.exists()){
			return file;
		}
		return null;
	}

	@Override
	public void deleteFileByIds(String[] ids) {
		for (String id : ids) {
			UploadFile uploadFile =uploadFileMapper.selectByPrimaryKey(id);
			deleteFileFormServer(uploadFile.getFilePath());
			uploadFileMapper.deleteByPrimaryKey(uploadFile.getId());
			
		}
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
		root += "\\" + DateUtil.dateToString(new Date(), "yyyyMM");
		createFolder(root);
		Random random = new Random();
		root += "\\" + type + "_" + new Date().getTime()
				+ (random.nextInt(100))
				+ fileName.substring(fileName.indexOf("."));
		return root;

	}

	private void createFolder(String path) {
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		
	}

	@Override
	public void delFile(String filePath) {
		deleteFileFormServer(filePath);
		deleteFileFormTable(filePath);
	}

	@Override
	public List<UploadFile> findUploadFile(Map<String,Object> map) {
		return uploadFileMapper.findByUploadFile(map);
	}


}
