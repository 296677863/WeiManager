package com.weiweb.common.model;

import java.io.Serializable;

public class UploadFile  implements Serializable{

	private static final long serialVersionUID = -3612811768891452060L;
	/**上传关联业务类型**/
	public String entityType;
	/**文件名**/
	public String fileName;
	/**中文名**/
	public String cnName;
	/**文件类型**/
	public String fileType;
	/**文件存放路径**/
	public String filePath;
	/**文件关联表实体id**/
	public String relationId;
	/**文件大小**/
	public Integer fileSize;
	
	public String coverPath;
	/**
	 * 压缩图片路径
	 */
	public String minPath;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	public Integer getFileSize() {
		return fileSize;
	}
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getCoverPath() {
		return coverPath;
	}
	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}
	public String getMinPath() {
		return minPath;
	}
	public void setMinPath(String minPath) {
		this.minPath = minPath;
	}
}
