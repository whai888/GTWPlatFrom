package com.sq.core.gtw.edit.controller.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.sq.core.gtw.edit.controller.command.FlowBizCreateConnectionCommand;
import com.sq.core.gtw.edit.controller.command.FlowBizReconnectConnectionCommand;

/**
 * @作者 whai
 * @创建日期 2013-7-5
 * @版本 V1.0
 * @文件名 FlowBizCustomGraphicalNodeEditPolicy.java
 */
public class FlowBizCustomGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

	@Override
	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		// TODO Auto-generated method stub
		//命令是从request中获得
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
		//创建连接的命令被记录
		request.setStartCommand(command);
		return command;
	}

	@Override
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		// TODO Auto-generated method stub
		//拖动句柄重新连接的Source端
		FlowBizReconnectConnectionCommand command = new FlowBizReconnectConnectionCommand();
		command.setConnectionModel(request.getConnectionEditPart().getModel());
		command.setNewSource(getHost().getModel());
		return command;
	}

	@Override
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		// TODO Auto-generated method stub
		//拖动句柄重新连接的Target端
		FlowBizReconnectConnectionCommand command = new FlowBizReconnectConnectionCommand();
		command.setConnectionModel(request.getConnectionEditPart().getModel());
		command.setNewTarget(getHost().getModel());
		return command;
	}

}
