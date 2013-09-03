package com.sq.core.gtw.edit.controller.command;

import org.eclipse.gef.commands.Command;

import com.sq.core.gtw.edit.model.IConnectionModel;

/**
 * @���� whai
 * @�������� 2013-7-5
 * @�汾 V1.0
 * @�ļ��� FlowBizReconnectConnectionCommand.java
 */
public class FlowBizReconnectConnectionCommand extends Command {
	private Object source , target ;	//����ģ��һ��������㣬һ�������յ�
	private IConnectionModel connection;	//���ӵ�ģ��
	@Override
	public void execute() {
		//ִ�е�ʱ����������������������յ�
		connection.attachSource();
		connection.attachTarget();
	}
	
	//�ж��Ƿ���ִ������
	@Override
	public boolean canExecute() {
		// TODO Auto-generated method stub
		//������ӵ������յ�Ϊͬһ���ڵ㣬���ܽ�������
		if(source == null || target == null )
			return false ;
		if(source.equals(target))
			return false ;
		return true;
	}

	/**
	 * ����Undo��ʱ��ҲҪ���������������ͳ����յ�
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
