package com.sq.core.gtw.view.pub.vo;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @作者 whai
 * @创建日期 2013-7-14
 * @版本 V1.0
 * @文件名 FlowMgnBizInfo.java
 */
@SuppressWarnings("serial")
@XStreamAlias("biz")
public class FlowMgnBizInfo implements Serializable{
	
	private String code ;
	
	private String name ;
	
	private List<FlowMgnBizInfo> biz ;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@SuppressWarnings("unchecked")
	public List getBiz() {
		return biz;
	}

	@SuppressWarnings("unchecked")
	public void setBiz(List biz) {
		this.biz = biz;
	}

}
