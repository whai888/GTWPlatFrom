package com.sq.core.gtw.edit.model.impl;

import com.sq.core.gtw.edit.model.IFlowModelProperties;

/**
 *@����  whai 
 *@�������� 2013-8-19
 *@�汾 V1.0
 *@�ļ��� BizModelPropertiesSystem.java
 */
public class BizModelPropertiesSystem implements IFlowModelProperties {

	private String name ;
	
	private String type ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
