package com.sq.core.gtw.edit.model.impl;

import com.sq.core.gtw.edit.model.IActionModelProperties;

/**
 *@作者  whai 
 *@创建日期 2013-8-10
 *@版本 V1.0
 *@文件名 ActionModelPropertiesAction.java
 */
public class ActionModelPropertiesAction implements IActionModelProperties {
	//跳转的action
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
