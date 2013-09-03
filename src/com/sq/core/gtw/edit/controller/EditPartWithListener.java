package com.sq.core.gtw.edit.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.sq.core.gtw.edit.model.impl.AbstractModel;

/**
 * @���� whai
 * @�������� 2013-7-3
 * @�汾 V1.0
 * @�ļ��� EditPartWithListener.java
 */
public class EditPartWithListener extends AbstractGraphicalEditPart implements
		PropertyChangeListener {

	
	/**
	 * ע�������
	 */
	@Override
	public void activate() {
		super.activate();
		// TODO Auto-generated method stub
		//EditPart ���Լ�ע��Ϊ������
		((AbstractModel)getModel()).addPropertyChangeLinstener(this);
	}

	
	/**
	 * ɾ��������
	 */
	@Override
	public void deactivate() {
		super.deactivate();
		// TODO Auto-generated method stub
		//ɾ���Լ��ļ�����
		((AbstractModel)getModel()).removePropertyChanageListener(this);
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
	}

	@Override
	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}

}
