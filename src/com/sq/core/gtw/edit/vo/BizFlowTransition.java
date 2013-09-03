package com.sq.core.gtw.edit.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @作者 whai
 * @创建日期 2013-7-17
 * @版本 V1.0
 * @文件名 BizFlowTransition.java
 */
@XStreamAlias("transition")
public class BizFlowTransition {

	private String name;
	private String dest;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

}
