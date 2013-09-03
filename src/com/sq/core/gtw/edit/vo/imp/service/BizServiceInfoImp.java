package com.sq.core.gtw.edit.vo.imp.service;

import com.sq.core.gtw.edit.vo.IBizServiceInfo;

/**
 *@作者  whai 
 *@创建日期 2013-8-17
 *@版本 V1.0
 *@文件名 BizServiceInfoImp.java
 */
public class BizServiceInfoImp implements IBizServiceInfo {
	
	private String name = "" ;
	
	private String desc = "" ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

}
