package com.sq.core.gtw.edit.model.impl;

import com.sq.core.gtw.edit.model.IFlowModelProperties;

/**
 *@作者  whai 
 *@创建日期 2013-8-19
 *@版本 V1.0
 *@文件名 BizModelPropertiesSystem.java
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
