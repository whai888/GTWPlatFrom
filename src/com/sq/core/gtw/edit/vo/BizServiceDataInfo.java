package com.sq.core.gtw.edit.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @���� whai
 * @�������� 2013-7-15
 * @�汾 V1.0
 * @�ļ��� BizServiceDataInfo.java
 */
@XStreamAlias("data")
public class BizServiceDataInfo {

	private String code;
	private String type;
	private String name;
	private String length;
	private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
