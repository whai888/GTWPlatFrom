package com.sq.core.gtw.edit.model.impl;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * @作者 whai
 * @创建日期 2013-7-3
 * @版本 V1.0
 * @文件名 AbstractModel.java
 */
public class AbstractModel implements IPropertySource{
	
	@XStreamOmitField
	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	
	public void addPropertyChangeLinstener (PropertyChangeListener listener){
		getInstanceofListeners().addPropertyChangeListener(listener);
	}

	public void firePropertyChange(String propName , Object oldValue , Object newValue){
		getInstanceofListeners().firePropertyChange(propName, oldValue, newValue);
	}
	
	public void removePropertyChanageListener(PropertyChangeListener listener){
		getInstanceofListeners().removePropertyChangeListener(listener);
	}

	public PropertyChangeSupport getInstanceofListeners(){
		if(listeners == null)
			listeners = new PropertyChangeSupport(this);
		return listeners ;
	}
	
	@Override
	public Object getEditableValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		// TODO Auto-generated method stub
		//如果在抽象模型中返回null会出现异常，因此这里返回一个长度为0的数组
		return new IPropertyDescriptor[0];
	}

	@Override
	public Object getPropertyValue(Object id) {
		// TODO Auto-generated method stub
		//返回模型自身作为可编辑的属性值
		return this;
	}

	@Override
	public boolean isPropertySet(Object id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		// TODO Auto-generated method stub
		
	}
}
