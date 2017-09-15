package com.weiweb.common.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.weiweb.common.model.UploadFile;
import com.weiweb.common.service.UploadFileService;
import com.weiweb.common.utils.DateUtil;
import com.weiweb.common.utils.VideoThumbTaker;
import com.weiweb.core.shiro.cache.impl.SystemConfigCache;
import com.weiweb.core.statics.Constant;

import net.coobird.thumbnailator.Thumbnails;


@Service
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
		//this.save(uploadfile);
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
		
	}

	@Override
	public List<UploadFile> findUploadFile(UploadFile up) {
		return null;
	}

}
