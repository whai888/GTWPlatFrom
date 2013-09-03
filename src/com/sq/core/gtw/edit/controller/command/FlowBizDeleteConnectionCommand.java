package com.sq.core.gtw.edit.controller.command;

import org.eclipse.gef.commands.Command;

import com.sq.core.gtw.edit.model.IConnectionModel;

/**
 * @作者 whai
 * @创建日期 2013-7-5
 * @版本 V1.0
 * @文件名 FlowBizDeleteConnectionCommand.java
 */
public class FlowBizDeleteConnectionCommand extends Command {
	
	private IConnectionModel connection ;

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		connection.detachSource();
		connection.detachTaret();
	}
	
	public void setConnectionModel(Object model){
		connection = (IConnectionModel)model;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		connection.attachSource();
		connection.attachTarget();
	}
	

}
