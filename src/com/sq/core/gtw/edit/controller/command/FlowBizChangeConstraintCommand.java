package com.sq.core.gtw.edit.controller.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.sq.core.gtw.edit.model.IFlowModel;

/**
 * @作者 whai
 * @创建日期 2013-7-3
 * @版本 V1.0
 * @文件名 FlowBizChangeConstraintCommand.java
 */
public class FlowBizChangeConstraintCommand extends Command {
	private IFlowModel flowModel ;
	private Rectangle constraint ;
	private Rectangle oldConstraint ;
	@Override
	public void execute() {
		//执行命名的方法    此方法很重要
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
	 * 撤销
	 */
	@Override
	public void undo() {
		// TODO Auto-generated method stub
		flowModel.setConstraint(oldConstraint);
	}
	

}
