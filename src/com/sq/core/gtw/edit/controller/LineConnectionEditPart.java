package com.sq.core.gtw.edit.controller;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.ui.part.EditorPart;

import com.sq.core.gtw.edit.controller.policy.FlowBizCustomConnectionEditPolicy;
import com.sq.core.gtw.edit.controller.policy.FlowBizCustomConnectionEndPointEditPolicy;

/**
 * @���� whai
 * @�������� 2013-7-5
 * @�汾 V1.0
 * @�ļ��� LineConnectionEditPart.java
 */
public class LineConnectionEditPart extends AbstractConnectionEditPart {

	private EditorPart editorPart ;
	
	public LineConnectionEditPart(EditorPart editorPart) {
		super();
		this.editorPart = editorPart;
	}

	@Override
	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		//���Ƕ���������
		PolylineConnection connection = new PolylineConnection();
		//�����������������
		connection.setTargetDecoration(new PolygonDecoration());
		return connection;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		//��������
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new FlowBizCustomConnectionEndPointEditPolicy());
		//ɾ������
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new FlowBizCustomConnectionEditPolicy());
	}

	public EditorPart getEditorPart() {
		return editorPart;
	}

	
}
