package com.sq.core.gtw.edit.controller.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.sq.core.gtw.edit.controller.command.FlowBizDeleteCommand;

/**
 * @作者 whai
 * @创建日期 2013-7-5
 * @版本 V1.0
 * @文件名 FlowBizCustomCompoentEditPolicy.java
 */
public class FlowBizCustomCompoentEditPolicy extends ComponentEditPolicy {

	//重载createDeleteCommand
	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		// TODO Auto-generated method stub
		//调用DeleteCommand
		FlowBizDeleteCommand deleteCommand = new FlowBizDeleteCommand();
		deleteCommand.setContentsModel(getHost().getParent().getModel());
		deleteCommand.setflowModel(getHost().getModel());
		return deleteCommand ;
	}
	

}
