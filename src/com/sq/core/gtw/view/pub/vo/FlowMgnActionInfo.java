package com.sq.core.gtw.view.pub.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @���� whai
 * @�������� 2013-7-22
 * @�汾 V1.0
 * @�ļ��� FlowMgnActionInfo.java
 */
@XStreamAlias("action")
public class FlowMgnActionInfo {
	private String code;
	private String name;

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

}
