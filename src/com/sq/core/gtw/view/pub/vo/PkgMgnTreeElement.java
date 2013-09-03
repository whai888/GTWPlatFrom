package com.sq.core.gtw.view.pub.vo;

import java.util.List;

/**
 * @作者 whai
 * @创建日期 2013-7-2
 * @版本 V1.0
 * @文件名 PkgMgnTreeElement.java
 */
public interface PkgMgnTreeElement {

	//节点名称
	public String getName();
	//是否有子孙
	public boolean hasChildren();
	//获得所有子孙
	@SuppressWarnings("unchecked")
	public List getChildren();
}
