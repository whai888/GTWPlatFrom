package com.sq.core.gtw.edit.controller.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.sq.core.gtw.edit.controller.command.FlowBizDeleteConnectionCommand;

/**
 * @���� whai
 * @�������� 2013-7-5
 * @�汾 V1.0
 * @�ļ��� FlowBizCustomConnectionEditPolicy.java
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
