package com.sq.core.gtw.edit.controller.command;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;

/**
 * @作者 whai
 * @创建日期 2013-7-5
 * @版本 V1.0
 * @文件名 FlowBizDiagramActionBarContributor.java
 */
public class FlowBizDiagramActionBarContributor extends ActionBarContributor {

	@Override
	protected void buildActions() {
		// TODO Auto-generated method stub
		addRetargetAction(new UndoRetargetAction());
		addRetargetAction(new RedoRetargetAction());
		addRetargetAction(new DeleteRetargetAction());
		//Retarget缩放Action
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
		//加上分割条
		toolBarManager.add(new Separator());
		//加上缩放按钮，注意这里的缩放Action的ID在GEF中已经定义了一些常熟
//		toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
//		toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));
	
	}

	
}
