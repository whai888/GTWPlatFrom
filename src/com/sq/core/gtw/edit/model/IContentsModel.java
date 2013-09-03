package com.sq.core.gtw.edit.model;

import java.util.List;

/**
 * @作者 whai
 * @创建日期 2013-7-9
 * @版本 V1.0
 * @文件名 IContentsModel.java
 */
public interface IContentsModel {

	/**
	 * 添加子模型
	 * @param child
	 */
	public void addChildren(Object child);
	/**
	 * 删除子模型
	 * @param child
	 */
	public void removeChildren(Object child);

	//得到子模型
	@SuppressWarnings("unchecked")
	public List getChildren();
	
}
