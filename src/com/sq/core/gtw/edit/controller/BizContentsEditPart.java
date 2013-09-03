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
 * @作者 whai
 * @创建日期 2013-7-3
 * @版本 V1.0
 * @文件名 ContentsEditPart.java
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
		//设置图层的布局
		figure.setLayoutManager(new XYLayout());
		return figure;
	}

	/**
	 * 安装相关的规则  用EditPolicy.LAYOUT_ROLE指定角色，如果角色都相同，则只有最后一个是有效的
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
		//模型改变是刷新视图
		if(evt.getPropertyName().equals(BizContentsModel.P_CHINLDREN));
			refreshChildren();
	}

	public EditorPart getEditorPart() {
		return editorPart;
	}

	
}
