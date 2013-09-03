package com.sq.core.gtw.edit.controller;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.part.EditorPart;

import com.sq.core.gtw.edit.controller.policy.FlowActionCustomXYLayoutEditPolicy;
import com.sq.core.gtw.edit.model.impl.ActionContentsModel;
import com.sq.core.gtw.edit.model.impl.ActionModel;

/**
 * @���� whai
 * @�������� 2013-7-3
 * @�汾 V1.0
 * @�ļ��� ContentsEditPart.java
 */
public class ActionContentsEditPart extends EditPartWithListener {
	
	private EditorPart editorPart ;

	public ActionContentsEditPart(EditorPart editorPart){
		this.editorPart = editorPart ;
	}
	
	@Override
	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		Layer figure = new Layer();
		//����ͼ��Ĳ���
		figure.setLayoutManager(new XYLayout());
		return figure;
	}

	/**
	 * ��װ��صĹ���  ��EditPolicy.LAYOUT_ROLEָ����ɫ�������ɫ����ͬ����ֻ�����һ������Ч��
	 */
//	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new FlowActionCustomXYLayoutEditPolicy());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getModelChildren() {
		// TODO Auto-generated method stub
		return ((ActionContentsModel)getModel()).getChildren();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		//ģ�͸ı���ˢ����ͼ
		if(evt.getPropertyName().equals(ActionContentsModel.P_CHINLDREN)){
			refreshChildren();
		}else if(evt.getPropertyName().equals(ActionModel.P_SOURCE_CONNECTION)){
			refreshSourceConnections();
		}else if(evt.getPropertyName().equals(ActionModel.P_TARGET_CONNTION)){
			refreshTargetConnections();
		}
	}

	public EditorPart getEditorPart() {
		return editorPart;
	}

}
