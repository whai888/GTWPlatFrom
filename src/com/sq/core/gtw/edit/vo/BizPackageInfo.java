package com.sq.core.gtw.edit.vo;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @作者 whai
 * @创建日期 2013-7-16
 * @版本 V1.0
 * @文件名 BizPackageInfo.java
 */
@XStreamAlias("package")
public class BizPackageInfo {
	private String chName ;
	private String code ;
	private String name ;
	private String packageType ;
	private String desc ;
	private List<DictMgnVo> dictList ;

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<DictMgnVo> getDictList() {
		if(dictList == null)
			dictList = new ArrayList<DictMgnVo>();
		return dictList;
	}

	public void setDictList(List<DictMgnVo> dictList) {
		this.dictList = dictList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
