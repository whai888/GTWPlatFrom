package com.sq.core.gtw.edit.controller.command;

import org.eclipse.gef.commands.Command;

import com.sq.core.gtw.edit.model.IContentsModel;
import com.sq.core.gtw.edit.model.IFlowModel;

/**
 * @作者 whai
 * @创建日期 2013-7-4
 * @版本 V1.0
 * @文件名 FlowBizCreateCommand.java
 */
public class FlowBizCreateCommand extends Command {
	
	private IContentsModel contentsModel ;
	private IFlowModel flowModel ;
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		contentsModel.addChildren(flowModel);
	}
	public void setContentsModel(Object contentsModel) {
		this.contentsModel = (IContentsModel) contentsModel;
	}
	public void setflowModel(Object flowModel) {
		this.flowModel = (IFlowModel) flowModel;
	}
	@Override
	public void undo() {
		// TODO Auto-generated method stub
		contentsModel.removeChildren(flowModel);
	}
	public IFlowModel getFlowModel() {
		return flowModel;
	}
	public IContentsModel getContentsModel() {
		return contentsModel;
	}
}
