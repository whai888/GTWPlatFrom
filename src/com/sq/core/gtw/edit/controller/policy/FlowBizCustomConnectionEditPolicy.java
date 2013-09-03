package com.sq.core.gtw.edit.controller.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.sq.core.gtw.edit.controller.command.FlowBizDeleteConnectionCommand;

/**
 * @作者 whai
 * @创建日期 2013-7-5
 * @版本 V1.0
 * @文件名 FlowBizCustomConnectionEditPolicy.java
 */
public class FlowBizCustomConnectionEditPolicy extends ConnectionEditPolicy {

	@Override
	protected Command getDeleteCommand(GroupRequest request) {
		// TODO Auto-generated method stub
		FlowBizDeleteConnectionCommand command = new FlowBizDeleteConnectionCommand();
		command.setConnectionModel(getHost().getModel());
		return command;
	}

}
