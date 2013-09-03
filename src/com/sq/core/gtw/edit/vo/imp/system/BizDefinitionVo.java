package com.sq.core.gtw.edit.vo.imp.system;
/**
 *@作者  whai 
 *@创建日期 2013-8-18
 *@版本 V1.0
 *@文件名 BizDefinitionVo.java
 */
public class BizDefinitionVo {
	
	private String label = "" ;
	
	private String labelLenth = "" ;
	
	private String type = "" ;
	
	private String [] combolStr ;
	
	private String classField = "" ;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getCombolStr() {
		return combolStr;
	}

	public void setCombolStr(String[] combolStr) {
		this.combolStr = combolStr;
	}

	public String getClassField() {
		return classField;
	}

	public void setClassField(String classField) {
		this.classField = classField;
	}

	public String getLabelLenth() {
		return labelLenth;
	}

	public void setLabelLenth(String labelLenth) {
		this.labelLenth = labelLenth;
	}

}
