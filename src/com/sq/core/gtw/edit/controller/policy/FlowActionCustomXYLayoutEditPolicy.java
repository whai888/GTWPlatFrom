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
 * @���� whai
 * @�������� 2013-7-3
 * @�汾 V1.0
 * @�ļ��� FlowActionCustomXYLayoutEditPolicy.java
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
		//��������ͼ�εĳߴ��λ��
		Rectangle rectangle = (Rectangle)getConstraintFor(request);
		//����´�����ͼ��
		ActionModel actionModel = (ActionModel)request.getNewObject();
		//Ϊ��ͼ������ǩ����õ�λ�úͳߴ�
		rectangle.setSize(80, 50);
		actionModel.setConstraint(rectangle);
		//���´�����ͼ����ӵ�ģ����
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
