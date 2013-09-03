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
 * @作者 whai
 * @创建日期 2013-7-5
 * @版本 V1.0
 * @文件名 LineConnectionEditPart.java
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
		//还是多义线连接
		PolylineConnection connection = new PolylineConnection();
		//不过这里加上了修饰
		connection.setTargetDecoration(new PolygonDecoration());
		return connection;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		//创建连接
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new FlowBizCustomConnectionEndPointEditPolicy());
		//删除连接
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new FlowBizCustomConnectionEditPolicy());
	}

	public EditorPart getEditorPart() {
		return editorPart;
	}

	
}
