package com.sq.core.gtw.edit.controller.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.sq.core.gtw.edit.controller.command.FlowBizCreateConnectionCommand;
import com.sq.core.gtw.edit.controller.command.FlowBizReconnectConnectionCommand;

/**
 * @���� whai
 * @�������� 2013-7-5
 * @�汾 V1.0
 * @�ļ��� FlowBizCustomGraphicalNodeEditPolicy.java
 */
public class FlowBizCustomGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

	@Override
	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		// TODO Auto-generated method stub
		//�����Ǵ�request�л��
		FlowBizCreateConnectionCommand command = (FlowBizCreateConnectionCommand) request.getStartCommand();
		command.setTarget(getHost().getModel());
		return command;
	}

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		// TODO Auto-generated method stub
		FlowBizCreateConnectionCommand command = new FlowBizCreateConnectionCommand();
		command.setConnection(request.getNewObject());
		command.setSource(getHost().getModel());
		//�������ӵ������¼
		request.setStartCommand(command);
		return command;
	}

	@Override
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		// TODO Auto-generated method stub
		//�϶�����������ӵ�Source��
		FlowBizReconnectConnectionCommand command = new FlowBizReconnectConnectionCommand();
		command.setConnectionModel(request.getConnectionEditPart().getModel());
		command.setNewSource(getHost().getModel());
		return command;
	}

	@Override
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		// TODO Auto-generated method stub
		//�϶�����������ӵ�Target��
		FlowBizReconnectConnectionCommand command = new FlowBizReconnectConnectionCommand();
		command.setConnectionModel(request.getConnectionEditPart().getModel());
		command.setNewTarget(getHost().getModel());
		return command;
	}

}
