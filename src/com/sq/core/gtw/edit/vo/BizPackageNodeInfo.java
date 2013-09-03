package com.sq.core.gtw.edit.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @作者 whai
 * @创建日期 2013-7-16
 * @版本 V1.0
 * @文件名 BizPackageNodeInfo.java
 */
@XStreamAlias("node")
public class BizPackageNodeInfo {
	private String name;
	private String xmltag;
	private String desc;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXmltag() {
		return xmltag;
	}

	public void setXmltag(String xmltag) {
		this.xmltag = xmltag;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
