package com.sq.core.gtw.edit.controller;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.part.EditorPart;

import com.sq.core.gtw.edit.controller.policy.FlowBizCustomXYLayoutEditPolicy;
import com.sq.core.gtw.edit.model.impl.BizContentsModel;

/**
 * @���� whai
 * @�������� 2013-7-3
 * @�汾 V1.0
 * @�ļ��� ContentsEditPart.java
 */
public class BizContentsEditPart extends EditPartWithListener {
	private EditorPart editorPart ;

	public BizContentsEditPart(EditorPart editorPart) {
		// TODO Auto-generated constructor stub
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
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new FlowBizCustomXYLayoutEditPolicy());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getModelChildren() {
		// TODO Auto-generated method stub
		return ((BizContentsModel)getModel()).getChildren();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		//ģ�͸ı���ˢ����ͼ
		if(evt.getPropertyName().equals(BizContentsModel.P_CHINLDREN));
			refreshChildren();
	}

	public EditorPart getEditorPart() {
		return editorPart;
	}

	
}
