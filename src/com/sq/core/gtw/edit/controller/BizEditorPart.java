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

import com.sq.core.gtw.edit.controller.policy.FlowBizCustomCompoentEditPolicy;
import com.sq.core.gtw.edit.controller.policy.FlowBizCustomGraphicalNodeEditPolicy;
import com.sq.core.gtw.edit.model.impl.BizModel;

/**
 * @作者 whai
 * @创建日期 2013-7-3
 * @版本 V1.0
 * @文件名 HelloEditorPart.java
 */
public class BizEditorPart extends EditPartWithListener implements NodeEditPart{
	
	private EditorPart editorPart ;

	public BizEditorPart(EditorPart editorPart) {
		// TODO Auto-generated constructor stub
		this.editorPart = editorPart ;
	}

	@Override
	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		BizModel model = (BizModel)getModel();
		Label label = new Label();
		label.setText(model.getText());
		label.setBorder(new CompoundBorder(new LineBorder(), new MarginBorder(3)));
		//设置背景色
		label.setBackgroundColor(ColorConstants.orange);
		//背景色不透明
		label.setOpaque(true);
		
		return label;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		//安装链接
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new FlowBizCustomGraphicalNodeEditPolicy());
		//删除模型
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new FlowBizCustomCompoentEditPolicy());
	}

	/**
	 * 把约束施加给图像
	 */
	@Override
	protected void refreshVisuals() {
		// TODO Auto-generated method stub
		Rectangle constraint =((BizModel) getModel()).getConstraint();
		((GraphicalEditPart)getParent()).setLayoutConstraint(this, getFigure(), constraint);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		//模型改变是刷新视图
		if(evt.getPropertyName().equals(BizModel.P_CONSTRAINT))
			refreshVisuals();
		else if(evt.getPropertyName().equals(BizModel.P_TEXT)){
			//当图形模型的文本属性改变时，在Graphical Editor中的图形文本也改变
			Label label = (Label)getFigure();
			label.setText((String) evt.getNewValue());
		}else if(evt.getPropertyName().equals(BizModel.P_SOURCE_CONNECTION)){
			refreshSourceConnections();
		}else if(evt.getPropertyName().equals(BizModel.P_TARGET_CONNTION)){
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
		return ((BizModel)getModel()).getModelSourceConnections();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List getModelTargetConnections() {
		// TODO Auto-generated method stub
		return ((BizModel)getModel()).getModelTargetConnections();
	}

	public EditorPart getEditorPart() {
		return editorPart;
	}

	
}
