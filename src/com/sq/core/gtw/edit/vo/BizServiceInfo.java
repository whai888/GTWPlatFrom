package com.sq.core.gtw.edit.vo;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @作者 whai
 * @创建日期 2013-7-16
 * @版本 V1.0
 * @文件名 BizServiceInfo.java
 */
@XStreamAlias("service")
public class BizServiceInfo {

	private String  name;
	private String serviceType;
	private String desc;
	private List<BizServiceInfo> subInfo = new ArrayList<BizServiceInfo>();

	public BizServiceInfo() {
		super();
	}

	public BizServiceInfo(String name, String serviceType, String desc) {
		super();
		this.name = name;
		this.serviceType = serviceType;
		this.desc = desc;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BizServiceInfo> getSubInfo() {
		if(subInfo == null)
			subInfo = new ArrayList<BizServiceInfo>();
		return subInfo;
	}

	public void setSubInfo(List<BizServiceInfo> subInfo) {
		this.subInfo = subInfo;
	}
	
	public boolean hasChildren(){
		if(subInfo !=null && this.subInfo.size() > 0 )
			return true ;
		else
			return false ;
	}

}
