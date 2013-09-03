package com.sq.core.gtw.edit.controller.command;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;

/**
 * @���� whai
 * @�������� 2013-7-5
 * @�汾 V1.0
 * @�ļ��� FlowBizDiagramActionBarContributor.java
 */
public class FlowBizDiagramActionBarContributor extends ActionBarContributor {

	@Override
	protected void buildActions() {
		// TODO Auto-generated method stub
		addRetargetAction(new UndoRetargetAction());
		addRetargetAction(new RedoRetargetAction());
		addRetargetAction(new DeleteRetargetAction());
		//Retarget����Action
//		addRetargetAction(new ZoomInRetargetAction());
//		addRetargetAction(new ZoomOutRetargetAction());

	}

	@Override
	protected void declareGlobalActionKeys() {
		// TODO Auto-generated method stub

	}

	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		// TODO Auto-generated method stub
		toolBarManager.add(getAction(ActionFactory.UNDO.getId()));
		toolBarManager.add(getAction(ActionFactory.REDO.getId()));
		toolBarManager.add(getAction(ActionFactory.DELETE.getId()));
		//���Ϸָ���
		toolBarManager.add(new Separator());
		//�������Ű�ť��ע�����������Action��ID��GEF���Ѿ�������һЩ����
//		toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
//		toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));
	
	}

	
}
