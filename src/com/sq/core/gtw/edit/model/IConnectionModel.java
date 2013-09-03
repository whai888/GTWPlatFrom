package com.sq.core.gtw.edit.model;
/**
 * @作者 whai
 * @创建日期 2013-7-5
 * @版本 V1.0
 * @文件名 AbstractConnectionModel.java
 */
public interface IConnectionModel {
	
	//链接的头端添加到source
	public void attachSource();
	
	//链接的尾端添加到target
	public void attachTarget();
	
	//移除源节点
	public void detachSource();

	//移除链接的尾端
	public void detachTaret();

	public Object getSource();

	public void setSource(Object source) ;

	public Object getTarget();

	public void setTarget(Object target) ;
}
