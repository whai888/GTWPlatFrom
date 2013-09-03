package com.sq.core.gtw.edit.controller.command;

import org.eclipse.gef.commands.Command;

import com.sq.core.gtw.edit.model.IConnectionModel;

/**
 * @作者 whai
 * @创建日期 2013-7-5
 * @版本 V1.0
 * @文件名 FlowBizCreateConnectionCommand.java
 */
public class FlowBizCreateConnectionCommand extends Command {
	private Object source , target ;	//两个模型一个用于起点，一个用于终点
	private IConnectionModel connection;	//连接的模型
	
	//首先判断是否能执行链接
	@Override
	public boolean canExecute() {
		// TODO Auto-generated method stub
		if(source == null || target == null)
			return false ;
		if(source.equals(target))
			return false ;
		return true;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		//执行的时候分两步：连接起点和连接终点
		connection.attachSource();
		connection.attachTarget();
	}

	public void setSource(Object model) {
		this.source = model;
		connection.setSource(source);
	}

	public void setTarget(Object model) {
		this.target =  model;
		connection.setTarget(target);
	}

	public void setConnection(Object model) {
		this.connection = (IConnectionModel) model;
	}

	/**
	 * 撤销Undo的时候也要分两步：撤销起点和撤销终点
	 */
	@Override
	public void undo() {
		// TODO Auto-generated method stub
		connection.detachSource();
		connection.detachTaret();
	}
	
	

}
