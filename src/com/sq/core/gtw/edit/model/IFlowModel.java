package com.sq.core.gtw.edit.model;

import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

/**
 * @���� whai
 * @�������� 2013-7-8
 * @�汾 V1.0
 * @�ļ��� IFlowModel.java
 */
public interface IFlowModel {
	public String getText();

	public void setText(String text) ;

	public Rectangle getConstraint();

	public void setConstraint(Rectangle rect);

	public IPropertyDescriptor[] getPropertyDescriptors();

	public Object getPropertyValue(Object id) ;

	/**
	 * �ж�������ͼ�е�����ֵ�Ƿ�ı䣬���û��ָ���������򷵻�false
	 */
	public boolean isPropertySet(Object id) ;

	/**
	 * ����ָ��ID������ֵ����������Բ��ܸı����û�������ѧ�Ǹ��������κ�����
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
