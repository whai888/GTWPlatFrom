package com.sq.core.gtw.edit.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.sq.core.gtw.edit.model.impl.AbstractModel;

/**
 * @作者 whai
 * @创建日期 2013-7-3
 * @版本 V1.0
 * @文件名 EditPartWithListener.java
 */
public class EditPartWithListener extends AbstractGraphicalEditPart implements
		PropertyChangeListener {

	
	/**
	 * 注册监听器
	 */
	@Override
	public void activate() {
		super.activate();
		// TODO Auto-generated method stub
		//EditPart 把自己注册为监听器
		((AbstractModel)getModel()).addPropertyChangeLinstener(this);
	}

	
	/**
	 * 删除监听器
	 */
	@Override
	public void deactivate() {
		super.deactivate();
		// TODO Auto-generated method stub
		//删除自己的监听器
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
