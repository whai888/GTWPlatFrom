package com.sq.core.gtw.edit.controller.command;

import org.eclipse.gef.commands.Command;

import com.sq.core.gtw.edit.model.IConnectionModel;

/**
 * @作者 whai
 * @创建日期 2013-7-5
 * @版本 V1.0
 * @文件名 FlowBizReconnectConnectionCommand.java
 */
public class FlowBizReconnectConnectionCommand extends Command {
	private Object source , target ;	//两个模型一个用于起点，一个用于终点
	private IConnectionModel connection;	//连接的模型
	@Override
	public void execute() {
		//执行的时候分两步：连接起点和连接终点
		connection.attachSource();
		connection.attachTarget();
	}
	
	//判断是否能执行连接
	@Override
	public boolean canExecute() {
		// TODO Auto-generated method stub
		//如果连接的起点和终点为同一个节点，则不能进行连接
		if(source == null || target == null )
			return false ;
		if(source.equals(target))
			return false ;
		return true;
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

	public void setConnectionModel(Object model) {
		// TODO Auto-generated method stub
		this.connection = (IConnectionModel) model;
		
	}

	public void setNewSource(Object model) {
		// TODO Auto-generated method stub
		source = model ;
		connection.setSource(source);
		
	}

	public void setNewTarget(Object model) {
		// TODO Auto-generated method stub
		target = model ;
		connection.setTarget(target);
	}
	
}
