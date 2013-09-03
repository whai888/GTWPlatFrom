package com.sq.core.gtw.edit.controller;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.ui.part.EditorPart;

import com.sq.core.gtw.edit.controller.policy.FlowActionCustomCompoentEditPolicy;
import com.sq.core.gtw.edit.controller.policy.FlowBizCustomGraphicalNodeEditPolicy;
import com.sq.core.gtw.edit.model.impl.ActionModel;

/**
 * @���� whai
 * @�������� 2013-7-3
 * @�汾 V1.0
 * @�ļ��� ActionEditorPart.java
 */
public class ActionEditorPart extends EditPartWithListener implements NodeEditPart{
	
	private EditorPart editorPart ;

	public ActionEditorPart(EditorPart editorPart) {
		// TODO Auto-generated constructor stub
		this.editorPart = editorPart ;
	}

	@Override
	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		ActionModel model = (ActionModel)getModel();
		Label label = new Label();
		label.setText(model.getText());
		label.setBorder(new CompoundBorder(new LineBorder(), new MarginBorder(3)));
		//���ñ���ɫ
		label.setBackgroundColor(ColorConstants.orange);
		//����ɫ��͸��
		label.setOpaque(true);
		return label;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		//��װ����
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new FlowBizCustomGraphicalNodeEditPolicy());
		//ɾ��ģ��
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new FlowActionCustomCompoentEditPolicy());
	}

	/**
	 * ��Լ��ʩ�Ӹ�ͼ��
	 */
	@Override
	public void refreshVisuals() {
		// TODO Auto-generated method stub
		if(getModel() instanceof ActionModel){
			Rectangle constraint =((ActionModel) getModel()).getConstraint();
			((GraphicalEditPart)getParent()).setLayoutConstraint(this, getFigure(), constraint);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		//ģ�͸ı���ˢ����ͼ
		if(evt.getPropertyName().equals(ActionModel.P_CONSTRAINT))
			refreshVisuals();
		else if(evt.getPropertyName().equals(ActionModel.P_TEXT)){
			//��ͼ��ģ�͵��ı����Ըı�ʱ����Graphical Editor�е�ͼ���ı�Ҳ�ı�
			Label label = (Label)getFigure();
			label.setText((String) evt.getNewValue());
		}else if(evt.getPropertyName().equals(ActionModel.P_SOURCE_CONNECTION)){
			refreshSourceConnections();
		}else if(evt.getPropertyName().equals(ActionModel.P_TARGET_CONNTION)){
			refreshTargetConnections();
		}
		
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart arg0) {
		// TODO Auto-generated method stub
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request arg0) {
		// TODO Auto-generated method stub
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart arg0) {
		// TODO Auto-generated method stub
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request arg0) {
		// TODO Auto-generated method stub
		return new ChopboxAnchor(getFigure());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List getModelSourceConnections() {
		// TODO Auto-generated method stub
		return ((ActionModel)getModel()).getModelSourceConnections();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List getModelTargetConnections() {
		// TODO Auto-generated method stub
		return ((ActionModel)getModel()).getModelTargetConnections();
	}

	public EditorPart getEditorPart() {
		return editorPart;
	}
}
