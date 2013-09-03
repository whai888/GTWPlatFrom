package com.sq.core.gtw.edit.vo;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @作者 whai
 * @创建日期 2013-6-24
 * @版本 V1.0
 * @文件名 DicvMgnVo.java
 */
@XStreamAlias("dict")
public class DictMgnVo {
	private String code;
	private String name;
	private String type;
	private String codeType;
	private String length;
	private String descrip;
	private String value ;
	//所有子孙
	private List<DictMgnVo> lists = new ArrayList<DictMgnVo>();
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean hasChildren(){
		if(lists.size() > 0 ){
			return true ;
		}
		return false ;
	}

	public List<DictMgnVo> getChildren() {
		return lists;
	}
	
	/**
	 * 添加子孙
	 * @param dictMgnVo
	 */
	public void add(DictMgnVo dictMgnVo){
		lists.add(dictMgnVo) ;
	}
}
