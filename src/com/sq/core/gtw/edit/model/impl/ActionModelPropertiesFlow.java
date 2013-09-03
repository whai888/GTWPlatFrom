package com.sq.core.gtw.edit.model.impl;

import com.sq.core.gtw.edit.model.IActionModelProperties;

/**
 *@作者 whai
 *@创建日期 2013-8-10
 *@版本 V1.0
 *@文件名 ActionModelPropertiesFlow.java
 */
public class ActionModelPropertiesFlow implements IActionModelProperties {
	//biz文件名
	private String bizInfo = "";

	@Override
	public String getBizInfo() {
		return bizInfo;
	}

	public void setBizInfo(String bizInfo) {
		this.bizInfo = bizInfo;
	}

	@Override
	public String getAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJsp() {
		// TODO Auto-generated method stub
		return null;
	}

}
