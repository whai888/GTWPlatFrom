package com.sq.core.gtw.edit.model.impl;

import com.sq.core.gtw.edit.model.IActionModelProperties;

/**
 *@����  whai 
 *@�������� 2013-8-10
 *@�汾 V1.0
 *@�ļ��� ActionModelPropertiesView.java
 */
public class ActionModelPropertiesView implements IActionModelProperties {

	private String jsp = "" ;

	@Override
	public String getJsp() {
		return jsp;
	}

	public void setJsp(String jsp) {
		this.jsp = jsp;
	}

	@Override
	public String getAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBizInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
