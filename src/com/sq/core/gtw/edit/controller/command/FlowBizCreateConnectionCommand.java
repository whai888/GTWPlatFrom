package com.sq.core.gtw.edit.controller.command;

import org.eclipse.gef.commands.Command;

import com.sq.core.gtw.edit.model.IConnectionModel;

/**
 * @���� whai
 * @�������� 2013-7-5
 * @�汾 V1.0
 * @�ļ��� FlowBizCreateConnectionCommand.java
 */
public class FlowBizCreateConnectionCommand extends Command {
	private Object source , target ;	//����ģ��һ��������㣬һ�������յ�
	private IConnectionModel connection;	//���ӵ�ģ��
	
	//�����ж��Ƿ���ִ������
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
		//ִ�е�ʱ����������������������յ�
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
	 * ����Undo��ʱ��ҲҪ���������������ͳ����յ�
	 */
	@Override
	public void undo() {
		// TODO Auto-generated method stub
		connection.detachSource();
		connection.detachTaret();
	}
	
	

}
