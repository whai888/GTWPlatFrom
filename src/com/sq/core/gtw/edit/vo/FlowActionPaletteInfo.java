package com.sq.core.gtw.edit.vo;

import com.sq.core.gtw.edit.model.IActionModelProperties;

/**
 *@���� whai
 *@�������� 2013-8-8
 *@�汾 V1.0
 *@�ļ��� FlowPaletteInfo.java
 */
public class FlowActionPaletteInfo {
	private String image;

	private String contentName ;
	
	private String name;
	
	//��Ӧģ�͵�����
	private IActionModelProperties iActionModelProperties ;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IActionModelProperties getiActionModelProperties() {
		return iActionModelProperties;
	}

	public void setiActionModelProperties(
			IActionModelProperties iActionModelProperties) {
		this.iActionModelProperties = iActionModelProperties;
	}

}
