package com.sq.core.gtw.edit.model.impl;

import com.sq.core.gtw.edit.model.IActionModelProperties;

/**
 *@����  whai 
 *@�������� 2013-8-10
 *@�汾 V1.0
 *@�ļ��� ActionModelPropertiesAction.java
 */
public class ActionModelPropertiesAction implements IActionModelProperties {
	//��ת��action
	private String action = "" ;

	@Override
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String getBizInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJsp() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
