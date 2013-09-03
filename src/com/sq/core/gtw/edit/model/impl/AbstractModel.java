package com.sq.core.gtw.edit.model.impl;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * @���� whai
 * @�������� 2013-7-3
 * @�汾 V1.0
 * @�ļ��� AbstractModel.java
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
		//����ڳ���ģ���з���null������쳣��������ﷵ��һ������Ϊ0������
		return new IPropertyDescriptor[0];
	}

	@Override
	public Object getPropertyValue(Object id) {
		// TODO Auto-generated method stub
		//����ģ��������Ϊ�ɱ༭������ֵ
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
