package com.sq.core.gtw.edit.vo.imp.service;

import com.sq.core.gtw.edit.vo.IBizServiceInfo;

/**
 *@���� whai
 *@�������� 2013-8-17
 *@�汾 V1.0
 *@�ļ��� FtpFileServiceInfo.java
 */
public class FtpFileServiceInfo implements IBizServiceInfo {

	// IP
	private String ip = "";
	// �˿�
	private String port = "";
	// �û���
	private String userName = "";
	// ����
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
