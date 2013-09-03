package com.sq.core.gtw.edit.vo.imp.service;

import com.sq.core.gtw.edit.vo.IBizServiceInfo;

/**
 *@作者 whai
 *@创建日期 2013-8-17
 *@版本 V1.0
 *@文件名 FtpFileServiceInfo.java
 */
public class FtpFileServiceInfo implements IBizServiceInfo {

	// IP
	private String ip = "";
	// 端口
	private String port = "";
	// 用户名
	private String userName = "";
	// 密码
	private String password = "";

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
