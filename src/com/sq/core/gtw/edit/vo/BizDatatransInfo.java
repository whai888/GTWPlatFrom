package com.sq.core.gtw.edit.vo;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @作者 whai
 * @创建日期 2013-7-15
 * @版本 V1.0
 * @文件名 BizDatatransInfo.java
 */
@XStreamAlias("transInfo")
public class BizDatatransInfo {
	
	private String id = "";
	private String name = "";
	private String desc = "";
	private List<DictMgnVo> input = new ArrayList<DictMgnVo>();
	private List<DictMgnVo> output = new ArrayList<DictMgnVo>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public List<DictMgnVo> getInput() {
		if( input == null)
			input = new ArrayList<DictMgnVo>(); 
		return input;
	}
	public void setInput(List<DictMgnVo> input) {
		this.input = input;
	}
	public List<DictMgnVo> getOutput() {
		if(output == null)
			output = new ArrayList<DictMgnVo>();
		return output;
	}
	public void setOutput(List<DictMgnVo> output) {
		this.output = output;
	}
	
	
}
