package com.sq.core.gtw.edit.controller.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.sq.core.gtw.edit.model.IFlowModel;

/**
 * @���� whai
 * @�������� 2013-7-3
 * @�汾 V1.0
 * @�ļ��� FlowBizChangeConstraintCommand.java
 */
public class FlowBizChangeConstraintCommand extends Command {
	private IFlowModel flowModel ;
	private Rectangle constraint ;
	private Rectangle oldConstraint ;
	@Override
	public void execute() {
		//ִ�������ķ���    �˷�������Ҫ
		// TODO Auto-generated method stub
		flowModel.setConstraint(constraint);
		oldConstraint = constraint ;
	}
	public void setModel(Object flowModel) {
		this.flowModel = (IFlowModel)flowModel;
	}
	public void setConstraint(Rectangle constraint) {
		this.constraint = constraint;
	}
	
	/**
	 * ����
	 */
	@Override
	public void undo() {
		// TODO Auto-generated method stub
		flowModel.setConstraint(oldConstraint);
	}
	

}
