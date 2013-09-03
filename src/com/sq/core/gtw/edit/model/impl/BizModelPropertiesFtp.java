package com.sq.core.gtw.edit.model.impl;

import com.sq.core.gtw.edit.model.IFlowModelProperties;

/**
 *@作者  whai 
 *@创建日期 2013-8-14
 *@版本 V1.0
 *@文件名 BizModelPropertiesFtp.java
 */
public class BizModelPropertiesFtp implements IFlowModelProperties {
	
	private String sendType = ""; 
	
	private String sendStype = "" ;

	private String localFilePath = "" ;
	
	private String localFileName = "" ;
	
	private String remoteFilePath = "" ;
	
	private String remoteFileName = "" ;
	
	private String service = "" ;

	public String getLocalFilePath() {
		return localFilePath;
	}

	public void setLocalFilePath(String localFilePath) {
		this.localFilePath = localFilePath;
	}

	public String getRemoteFilePath() {
		return remoteFilePath;
	}

	public void setRemoteFilePath(String remoteFilePath) {
		this.remoteFilePath = remoteFilePath;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getSendStype() {
		return sendStype;
	}

	public void setSendStype(String sendStype) {
		this.sendStype = sendStype;
	}

	public String getLocalFileName() {
		return localFileName;
	}

	public void setLocalFileName(String localFileName) {
		this.localFileName = localFileName;
	}

	public String getRemoteFileName() {
		return remoteFileName;
	}

	public void setRemoteFileName(String remoteFileName) {
		this.remoteFileName = remoteFileName;
	}
	
	
}
