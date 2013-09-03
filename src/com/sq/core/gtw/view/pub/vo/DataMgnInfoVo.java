package com.sq.core.gtw.view.pub.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @作者 whai
 * @创建日期 2013-7-23
 * @版本 V1.0
 * @文件名 DataMgnInfoVo.java
 */
@XStreamAlias("data")
public class DataMgnInfoVo {

	private String tableName;
	private String cnName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

}
