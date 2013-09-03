package com.sq.core.gtw.edit.controller.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.sq.core.gtw.edit.controller.command.FlowBizDeleteCommand;

/**
 * @���� whai
 * @�������� 2013-7-5
 * @�汾 V1.0
 * @�ļ��� FlowBizCustomCompoentEditPolicy.java
 */
public class FlowBizCustomCompoentEditPolicy extends ComponentEditPolicy {

	//����createDeleteCommand
	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		// TODO Auto-generated method stub
		//����DeleteCommand
		FlowBizDeleteCommand deleteCommand = new FlowBizDeleteCommand();
		deleteCommand.setContentsModel(getHost().getParent().getModel());
		deleteCommand.setflowModel(getHost().getModel());
		return deleteCommand ;
	}
	

}
