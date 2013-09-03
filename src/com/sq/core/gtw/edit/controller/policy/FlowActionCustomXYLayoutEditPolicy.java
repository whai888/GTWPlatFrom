package com.sq.core.gtw.edit.controller.policy;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.sq.core.gtw.edit.controller.command.FlowBizChangeConstraintCommand;
import com.sq.core.gtw.edit.controller.command.FlowBizCreateCommand;
import com.sq.core.gtw.edit.model.impl.ActionModel;

/**
 * @作者 whai
 * @创建日期 2013-7-3
 * @版本 V1.0
 * @文件名 FlowActionCustomXYLayoutEditPolicy.java
 */
public class FlowActionCustomXYLayoutEditPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		// TODO Auto-generated method stub
		FlowBizChangeConstraintCommand command = new FlowBizChangeConstraintCommand();
		command.setModel(child.getModel());
		command.setConstraint((Rectangle)constraint);
		return command;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		// TODO Auto-generated method stub
		FlowBizCreateCommand command = new FlowBizCreateCommand();
		//产生创建图形的尺寸和位置
		Rectangle rectangle = (Rectangle)getConstraintFor(request);
		//获得新创建的图形
		ActionModel actionModel = (ActionModel)request.getNewObject();
		//为该图形设置签名获得的位置和尺寸
		rectangle.setSize(80, 50);
		actionModel.setConstraint(rectangle);
		//将新创建的图形添加到模型中
		command.setContentsModel(getHost().getModel());
		command.setflowModel(actionModel);
		return command;
	}

	@Override
	public Command getCommand(Request request) {
		// TODO Auto-generated method stub
		return super.getCommand(request);
	}

}
