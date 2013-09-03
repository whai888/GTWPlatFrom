package com.sq.core.gtw.edit.model;

import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

/**
 * @作者 whai
 * @创建日期 2013-7-8
 * @版本 V1.0
 * @文件名 IFlowModel.java
 */
public interface IFlowModel {
	public String getText();

	public void setText(String text) ;

	public Rectangle getConstraint();

	public void setConstraint(Rectangle rect);

	public IPropertyDescriptor[] getPropertyDescriptors();

	public Object getPropertyValue(Object id) ;

	/**
	 * 判断属性视图中的属性值是否改变，如果没有指定的属性则返回false
	 */
	public boolean isPropertySet(Object id) ;

	/**
	 * 设置指定ID的属性值，如果该属性不能改变或者没有这个数学那个，则不做任何事情
	 */
	public void setPropertyValue(Object id, Object value) ;
	
	public void addSourceConnection(Object connx);
	
	public void addTargetConnection(Object connx);
	
	@SuppressWarnings("unchecked")
	public List getModelSourceConnections();
	
	@SuppressWarnings("unchecked")
	public List getModelTargetConnections();
	
	public void removeSourceConnection(Object connx);
	
	public void removeTargetConnection(Object connx);

	@SuppressWarnings("unchecked")
	public void setModelSourceConnections(List sourceConnections) ;
	
	@SuppressWarnings("unchecked")
	public void setModelTargetConnections(List targetConnection ) ;
}
