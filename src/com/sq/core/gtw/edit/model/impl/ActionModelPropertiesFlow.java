package com.sq.core.gtw.edit.model.impl;

import com.sq.core.gtw.edit.model.IActionModelProperties;

/**
 *@���� whai
 *@�������� 2013-8-10
 *@�汾 V1.0
 *@�ļ��� ActionModelPropertiesFlow.java
 */
public class ActionModelPropertiesFlow implements IActionModelProperties {
	//biz�ļ���
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
